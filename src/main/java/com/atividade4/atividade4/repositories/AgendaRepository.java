package com.atividade4.atividade4.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atividade4.atividade4.entities.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda,Long>{

	List<Agenda> findByProfessorId(Long professorId);
	
	@Query("SELECT COUNT(a) > 0 FROM Agenda a WHERE a.professor.id = :professorId " +
	           "AND ((a.dataInicio <= :dataFim AND a.dataFim >= :dataInicio))")
	    boolean existsAgendamentoConflitante(
	            @Param("professorId") Long professorId,
	            @Param("dataInicio") LocalDate dataInicio,
	            @Param("dataFim") LocalDate dataFim);
}
