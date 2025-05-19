package com.atividade4.atividade4.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessoresHabilitadosRequestDTO {

	private Long cursoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}
