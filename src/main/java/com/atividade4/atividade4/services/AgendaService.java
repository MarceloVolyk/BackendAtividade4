package com.atividade4.atividade4.services;

import java.util.List;

import com.atividade4.atividade4.dtos.AgendaDTO;
import com.atividade4.atividade4.dtos.AgendaResponseDTO;
import com.atividade4.atividade4.dtos.ProfessoresHabilitadosRequestDTO;
import com.atividade4.atividade4.dtos.ResumoTreinamentoDTO;
import com.atividade4.atividade4.entities.Agenda;
import com.atividade4.atividade4.entities.Professor;

public interface AgendaService {

	public List<AgendaResponseDTO> listarTodasAgendas();
	public AgendaResponseDTO obterAgendaPorId(Long id);
	public List<AgendaResponseDTO> listarAgendasPorProfessor(Long professorId);
	public AgendaResponseDTO criarAgenda(AgendaDTO agendaDTO);
	public AgendaResponseDTO atualizarResumoTreinamento(Long id, ResumoTreinamentoDTO resumoDTO);
	public List<Professor> obterProfessoresHabilitadosDisponiveis(ProfessoresHabilitadosRequestDTO requestDTO);
	
}
