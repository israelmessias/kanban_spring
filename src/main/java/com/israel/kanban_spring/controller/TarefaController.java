package com.israel.kanban_spring.controller;

import com.israel.kanban_spring.model.dto.TarefaCreateDTO;
import com.israel.kanban_spring.model.entity.Tarefa;
import com.israel.kanban_spring.service.impl.TarefaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaServiceImpl service;

    @GetMapping
    public String hello (){
        return "Tarefa";
    }

    @PostMapping
    public ResponseEntity criarTarefa(@RequestBody TarefaCreateDTO tarefaCreateDTO){

        try {
            Tarefa tarefa = service.converterCreate(tarefaCreateDTO);
            service.criar(tarefa);
            return new ResponseEntity(tarefa, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
