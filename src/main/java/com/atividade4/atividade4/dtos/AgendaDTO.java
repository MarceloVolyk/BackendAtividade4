package com.atividade4.atividade4.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {

	private Long id;
	private Long cursoId;
	private Long professorId;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private String cidade;
	private String estado;
	private String cep;
	private String resumoTreinamento;
}
