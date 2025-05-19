package com.atividade4.atividade4.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Agenda {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "curso_id", nullable = false)
	    private Curso curso;
	    
	    @ManyToOne
	    @JoinColumn(name = "professor_id", nullable = false)
	    private Professor professor;
	    
	    @Column(nullable = false)
	    private LocalDate dataInicio;
	    
	    @Column(nullable = false)
	    private LocalDate dataFim;
	    
	    @Column(nullable = false)
	    private LocalTime horaInicio;
	    
	    @Column(nullable = false)
	    private LocalTime horaFim;
	    
	    @Column(nullable = false)
	    private String cidade;
	    
	    @Column(nullable = false)
	    private String estado;
	    
	    @Column(nullable = false)
	    private String cep;
	    
	    private String resumoTreinamento;

		public Agenda(Long id, Curso curso, Professor professor, LocalDate dataInicio, LocalDate dataFim,
				LocalTime horaInicio, LocalTime horaFim, String cidade, String estado, String cep) {
			super();
			this.id = id;
			this.curso = curso;
			this.professor = professor;
			this.dataInicio = dataInicio;
			this.dataFim = dataFim;
			this.horaInicio = horaInicio;
			this.horaFim = horaFim;
			this.cidade = cidade;
			this.estado = estado;
			this.cep = cep;
		}

	
	
	
	
}
