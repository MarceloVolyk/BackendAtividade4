package com.atividade4.atividade4.services;

import com.atividade4.atividade4.dtos.CursoGetDTO;
import com.atividade4.atividade4.dtos.CursoPostDTO;
import com.atividade4.atividade4.entities.Curso;

public interface CursoService {

	public void salvarCurso(CursoPostDTO cursoPostDTO);
	public CursoGetDTO buscarCursoPorIdComProf(Long id);
	Curso buscarPorId(Long id);
}
