package com.atividade4.atividade4.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaResponseDTO {

	private Long id;
    private String curso;
    private String professor;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private String cidade;
    private String estado;
    private String cep;
    private String resumoTreinamento;
}
