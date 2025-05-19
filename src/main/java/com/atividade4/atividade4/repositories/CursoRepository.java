package com.atividade4.atividade4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atividade4.atividade4.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso,Long>{

	@Query("SELECT DISTINCT c FROM Curso c LEFT JOIN FETCH c.professores WHERE c.id = :id")
    Optional<Curso> findByIdWithProfessores(@Param("id") Long id);
}
