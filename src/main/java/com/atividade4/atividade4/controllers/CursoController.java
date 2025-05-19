package com.atividade4.atividade4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atividade4.atividade4.dtos.CursoGetDTO;
import com.atividade4.atividade4.dtos.CursoPostDTO;
import com.atividade4.atividade4.services.CursoServiceImpl;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoServiceImpl cursoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CursoGetDTO> buscarCursoPorIdComProf(@PathVariable Long id) {
	    CursoGetDTO cursoGetDTO = cursoService.buscarCursoPorIdComProf(id);
	    return ResponseEntity.ok(cursoGetDTO);
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody CursoPostDTO cursoPostDTO) {
        cursoService.salvarCurso(cursoPostDTO);
    }
	
	@PostMapping("/{idCurso}/professores/{idProfessor}")
    public ResponseEntity<Void> vincularFuncionario(
            @PathVariable Integer idCurso,
            @PathVariable Integer idProfessor) {
        
        cursoService.vincularProfessor(
            idCurso.longValue(), 
            idProfessor.longValue()
        );
        return ResponseEntity.ok().build();
    }
}
