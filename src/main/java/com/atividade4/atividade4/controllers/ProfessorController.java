package com.atividade4.atividade4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atividade4.atividade4.dtos.AgendaResponseDTO;
import com.atividade4.atividade4.dtos.ProfessorGetDTO;
import com.atividade4.atividade4.dtos.ProfessorPostDTO;
import com.atividade4.atividade4.dtos.ResumoTreinamentoDTO;
import com.atividade4.atividade4.services.AgendaServiceImpl;
import com.atividade4.atividade4.services.ProfessorServiceImpl;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired
    private ProfessorServiceImpl professorService;

	@Autowired
    private AgendaServiceImpl agendaService;
	
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody ProfessorPostDTO professorPostDTO) {
        professorService.salvarProfessor(professorPostDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<ProfessorGetDTO>> obterTodos() {
        List<ProfessorGetDTO> professores = professorService.obterTodos();
        return ResponseEntity.ok(professores);
    }
    
 // Obter agendas do professor
    @GetMapping("/minhas-agendas/{professorId}")
    public ResponseEntity<List<AgendaResponseDTO>> listarAgendasDoProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok(agendaService.listarAgendasPorProfessor(professorId));
    }

    // Cadastrar resumo do treinamento
    @PatchMapping("/{id}/resumo")
    public ResponseEntity<AgendaResponseDTO> atualizarResumoTreinamento(
            @PathVariable Long id, 
            @RequestBody ResumoTreinamentoDTO resumoDTO) {
        return ResponseEntity.ok(agendaService.atualizarResumoTreinamento(id, resumoDTO));
    }
}
