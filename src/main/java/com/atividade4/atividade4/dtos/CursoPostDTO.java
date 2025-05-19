package com.atividade4.atividade4.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoPostDTO {

	private String nome;
	private String descricao;
	private Double cargaHoraria;
	private String objetivos;
	private String ementa;
}
