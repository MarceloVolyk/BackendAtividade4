package com.atividade4.atividade4.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade4.atividade4.dtos.AgendaDTO;
import com.atividade4.atividade4.dtos.AgendaResponseDTO;
import com.atividade4.atividade4.dtos.ProfessoresHabilitadosRequestDTO;
import com.atividade4.atividade4.dtos.ResumoTreinamentoDTO;
import com.atividade4.atividade4.entities.Agenda;
import com.atividade4.atividade4.entities.Curso;
import com.atividade4.atividade4.entities.Professor;
import com.atividade4.atividade4.exceptions.RegraNegocioException;
import com.atividade4.atividade4.exceptions.ValidationException;
import com.atividade4.atividade4.repositories.AgendaRepository;
import com.atividade4.atividade4.repositories.CursoRepository;
import com.atividade4.atividade4.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class AgendaServiceImpl implements AgendaService{

	@Autowired
    private AgendaRepository agendaRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    // Listar todas as agendas
    public List<AgendaResponseDTO> listarTodasAgendas() {
        return agendaRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    // Obter agenda por ID
    public AgendaResponseDTO obterAgendaPorId(Long id) {
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Agenda não encontrada com ID: " + id));
        return convertToResponseDTO(agenda);
    }
    
    // Listar agendas de um professor específico
    public List<AgendaResponseDTO> listarAgendasPorProfessor(Long professorId) {
        return agendaRepository.findByProfessorId(professorId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    // Criar uma nova agenda com validações
    @Transactional
    public AgendaResponseDTO criarAgenda(AgendaDTO agendaDTO) {
        // Verificar se o curso existe
        Curso curso = cursoRepository.findById(agendaDTO.getCursoId())
                .orElseThrow(() -> new RegraNegocioException("Curso não encontrado com ID: " + agendaDTO.getCursoId()));
        
        // Verificar se o professor existe
        Professor professor = professorRepository.findById(agendaDTO.getProfessorId())
                .orElseThrow(() -> new RegraNegocioException("Professor não encontrado com ID: " + agendaDTO.getProfessorId()));
        
        // Verificar se o professor está habilitado para o curso
        boolean professorHabilitado = professorRepository.isProfessorHabilitadoParaCurso(
                professor.getId(), curso.getId());
        
        if (!professorHabilitado) {
            throw new ValidationException("O professor não está habilitado para ministrar este curso");
        }
        
        // Verificar se o professor já está agendado para o período
        boolean professorIndisponivel = agendaRepository.existsAgendamentoConflitante(
                professor.getId(), agendaDTO.getDataInicio(), agendaDTO.getDataFim());
        
        if (professorIndisponivel) {
            throw new ValidationException("O professor já possui agendamento para este período");
        }
        
        // Validar datas
        if (agendaDTO.getDataFim().isBefore(agendaDTO.getDataInicio())) {
            throw new ValidationException("A data de fim não pode ser anterior à data de início");
        }
        
        // Criar a agenda
        Agenda agenda = new Agenda();
        agenda.setCurso(curso);
        agenda.setProfessor(professor);
        agenda.setDataInicio(agendaDTO.getDataInicio());
        agenda.setDataFim(agendaDTO.getDataFim());
        agenda.setHoraInicio(agendaDTO.getHoraInicio());
        agenda.setHoraFim(agendaDTO.getHoraFim());
        agenda.setCidade(agendaDTO.getCidade());
        agenda.setEstado(agendaDTO.getEstado());
        agenda.setCep(agendaDTO.getCep());
        
        Agenda agendaSalva = agendaRepository.save(agenda);
        
        return convertToResponseDTO(agendaSalva);
    }
    
 
    // Atualizar o resumo do treinamento
    @Transactional
    public AgendaResponseDTO atualizarResumoTreinamento(Long id, ResumoTreinamentoDTO resumoDTO) {
        Agenda agenda = agendaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Agenda não encontrada com ID: " + id));
        
        agenda.setResumoTreinamento(resumoDTO.getResumoTreinamento());
        Agenda agendaAtualizada = agendaRepository.save(agenda);
        
        return convertToResponseDTO(agendaAtualizada);
    }
    
    // Método para obter professores habilitados para um curso e disponíveis no período
    public List<Professor> obterProfessoresHabilitadosDisponiveis(ProfessoresHabilitadosRequestDTO requestDTO) {
        // Obter todos os professores habilitados para o curso
        List<Professor> professoresHabilitados = professorRepository.findProfessoresHabilitadosByCursoId(
                requestDTO.getCursoId());
        
        // Filtrar apenas os professores que estão disponíveis no período
        return professoresHabilitados.stream()
                .filter(professor -> !agendaRepository.existsAgendamentoConflitante(
                        professor.getId(), 
                        requestDTO.getDataInicio(), 
                        requestDTO.getDataFim()))
                .collect(Collectors.toList());
    }
    
    // Método auxiliar para converter Agenda para AgendaResponseDTO
    private AgendaResponseDTO convertToResponseDTO(Agenda agenda) {
        AgendaResponseDTO responseDTO = new AgendaResponseDTO();
        responseDTO.setId(agenda.getId());
        responseDTO.setCurso(agenda.getCurso().getDescricao());
        responseDTO.setProfessor(agenda.getProfessor().getNome());
        responseDTO.setDataInicio(agenda.getDataInicio());
        responseDTO.setDataFim(agenda.getDataFim());
        responseDTO.setHoraInicio(agenda.getHoraInicio());
        responseDTO.setHoraFim(agenda.getHoraFim());
        responseDTO.setCidade(agenda.getCidade());
        responseDTO.setEstado(agenda.getEstado());
        responseDTO.setCep(agenda.getCep());
        responseDTO.setResumoTreinamento(agenda.getResumoTreinamento());
        return responseDTO;
    }
}
