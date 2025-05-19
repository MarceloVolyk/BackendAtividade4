package com.atividade4.atividade4.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atividade4.atividade4.dtos.AgendaDTO;
import com.atividade4.atividade4.dtos.AgendaResponseDTO;
import com.atividade4.atividade4.dtos.ProfessoresHabilitadosRequestDTO;
import com.atividade4.atividade4.entities.Professor;
import com.atividade4.atividade4.services.AgendaServiceImpl;

public class AgendaController {

	@Autowired
    private AgendaServiceImpl agendaService;

    // Listar todas as agendas
    @GetMapping
    public ResponseEntity<List<AgendaResponseDTO>> listarTodasAgendas() {
        return ResponseEntity.ok(agendaService.listarTodasAgendas());
    }

    // Obter uma agenda específica
    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponseDTO> obterAgendaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendaService.obterAgendaPorId(id));
    }

    // Criar uma nova agenda
    @PostMapping
    public ResponseEntity<AgendaResponseDTO> criarAgenda(@RequestBody AgendaDTO agendaDTO) {
        return new ResponseEntity<>(agendaService.criarAgenda(agendaDTO), HttpStatus.CREATED);
    }

    // Atualizar uma agenda existente
    @PutMapping("/{id}")
    public ResponseEntity<AgendaResponseDTO> atualizarAgenda(@PathVariable Long id, @RequestBody AgendaDTO agendaDTO) {
        return ResponseEntity.ok(agendaService.atualizarAgenda(id, agendaDTO));
    }

    // Excluir uma agenda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgenda(@PathVariable Long id) {
        agendaService.excluirAgenda(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obter professores habilitados e disponíveis
    @PostMapping("/professores-disponiveis")
    public ResponseEntity<List<Professor>> obterProfessoresDisponiveis(@RequestBody ProfessoresHabilitadosRequestDTO requestDTO) {
        return ResponseEntity.ok(agendaService.obterProfessoresHabilitadosDisponiveis(requestDTO));
    }
}
