package com.atividade4.atividade4.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorPostDTO {

	private String nome;
	private String cpf;
	private String rg;
	private String endereco;
	private String celular;
}
