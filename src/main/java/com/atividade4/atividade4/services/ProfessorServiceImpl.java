package com.atividade4.atividade4.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade4.atividade4.dtos.ProfessorGetDTO;
import com.atividade4.atividade4.dtos.ProfessorPostDTO;
import com.atividade4.atividade4.entities.Professor;
import com.atividade4.atividade4.exceptions.RegraNegocioException;
import com.atividade4.atividade4.repositories.ProfessorRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService{

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Override
	public ProfessorGetDTO salvarProfessor(ProfessorPostDTO professorPostDTO) {
		Professor professor = new Professor();
		professor.setNome(professorPostDTO.getNome());
		professor.setCpf(professorPostDTO.getCpf());
		professor.setRg(professorPostDTO.getRg());
		professor.setEndereco(professorPostDTO.getEndereco());
		professor.setCelular(professorPostDTO.getCelular());

		Professor p = professorRepository.save(professor);
		ProfessorGetDTO profGetDTO = new ProfessorGetDTO();
		profGetDTO.setId(p.getId());
		profGetDTO.setNome(p.getNome());
		profGetDTO.setCpf(p.getCpf());
		profGetDTO.setRg(p.getRg());
		profGetDTO.setEndereco(p.getEndereco());
		profGetDTO.setCelular(p.getCelular());

		return profGetDTO;
	}

	@Override
	public List<ProfessorGetDTO> obterTodos() {
		List<Professor> professores = professorRepository.findAll();
        return professores.stream()
                   .map(professor -> new ProfessorGetDTO(
                		   professor.getId(), 
                		   professor.getNome(),
                   		   professor.getCpf(),
						   professor.getRg(),
						   professor.getEndereco(),
						   professor.getCelular()))
                   .collect(Collectors.toList());
	}
	
	public Professor buscarPorId(Long id) {
		return professorRepository.findById(id)
				.orElseThrow(() -> new RegraNegocioException("Professor não encontrado!"));
	}
}
