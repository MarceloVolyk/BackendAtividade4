package com.atividade4.atividade4.services;

import java.util.List;

import com.atividade4.atividade4.dtos.ProfessorGetDTO;
import com.atividade4.atividade4.dtos.ProfessorPostDTO;

public interface ProfessorService {

	public ProfessorGetDTO salvarProfessor(ProfessorPostDTO professorPostDTO);
	public List<ProfessorGetDTO> obterTodos();
}
