package com.atividade4.atividade4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade4.atividade4.dtos.CursoGetDTO;
import com.atividade4.atividade4.dtos.CursoPostDTO;
import com.atividade4.atividade4.dtos.ProfessorDTO;
import com.atividade4.atividade4.entities.Curso;
import com.atividade4.atividade4.entities.Professor;
import com.atividade4.atividade4.exceptions.RegraNegocioException;
import com.atividade4.atividade4.repositories.CursoRepository;
import com.atividade4.atividade4.repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class CursoServiceImpl implements CursoService{

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Transactional
    public void salvarCurso(CursoPostDTO cursoPostDTO) {
        Curso curso = new Curso();
        curso.setNome(cursoPostDTO.getNome());
        curso.setDescricao(cursoPostDTO.getDescricao());
        curso.setCargaHoraria(cursoPostDTO.getCargaHoraria());
        curso.setObjetivos(cursoPostDTO.getObjetivos());
        curso.setEmenta(cursoPostDTO.getEmenta());
        
        cursoRepository.save(curso);
    }
	
	@Override
	public CursoGetDTO buscarCursoPorIdComProf(Long id) {
	    Curso curso = cursoRepository.findByIdWithProfessores(id)
	        .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
	    
	    List<ProfessorDTO> professoresDTO = curso.getProfessores().stream()
	            .map(func -> new ProfessorDTO(func.getId(), func.getNome()))
	            .toList();
	    
	    return new CursoGetDTO(
	        curso.getId(),
	        curso.getNome(),
	        professoresDTO);
	}
	
	@Override
	public Curso buscarPorId(Long id) {
		return cursoRepository.findById(id)
				.orElseThrow(() -> new RegraNegocioException("Curso não encontrado!"));
	}
	
	@Transactional
    public void vincularProfessor(Long idCurso, Long idProfessor) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RegraNegocioException("Curso não encontrado"));
        
        Professor professor = professorRepository.findById(idProfessor)
                .orElseThrow(() -> new RegraNegocioException("Professor não encontrado"));
        
        curso.getProfessores().add(professor);
        cursoRepository.save(curso);
        
    }
}
