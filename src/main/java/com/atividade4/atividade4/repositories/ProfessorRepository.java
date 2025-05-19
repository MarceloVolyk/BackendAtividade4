package com.atividade4.atividade4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atividade4.atividade4.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,Long>{

	// Verificar se um professor está habilitado para um determinado curso
    @Query("SELECT COUNT(p) > 0 FROM Professor p JOIN p.cursos c WHERE p.id = :professorId AND c.id = :cursoId")
    boolean isProfessorHabilitadoParaCurso(@Param("professorId") Long professorId, @Param("cursoId") Long cursoId);
    
    // Buscar todos os professores habilitados para um determinado curso
    @Query("SELECT p FROM Professor p JOIN p.cursos c WHERE c.id = :cursoId")
    List<Professor> findProfessoresHabilitadosByCursoId(@Param("cursoId") Long cursoId);
}
