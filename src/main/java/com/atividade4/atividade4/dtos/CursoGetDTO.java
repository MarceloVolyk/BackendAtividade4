package com.atividade4.atividade4.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoGetDTO {

	private Long id;
	private String nome;
	private List<ProfessorDTO> professores;
}
