package com.israel.kanban_spring.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israel.kanban_spring.model.dto.TarefaCreateDTO;
import com.israel.kanban_spring.model.entity.Tarefa;
import com.israel.kanban_spring.service.impl.TarefaServiceImpl;

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
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity buscarTarefas(@Param(value = "id") Integer id){
    	try {
    		Set<Tarefa> tarefas = service.obterTarefas(id);
    		ResponseEntity.ok().body(tarefas);
    		return ResponseEntity.ok().body(tarefas);
    	}catch (Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
			// TODO: handle exception
		}
    }
}
