package com.israel.kanban_spring.controller;

import com.israel.kanban_spring.Service.impl.UsuarioServiceImpl;
import com.israel.kanban_spring.model.dto.UsuarioDTO;
import com.israel.kanban_spring.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioServiceImpl service;

    @GetMapping
    public String hello(){
        return "Hello";
    }

    @PostMapping
    public ResponseEntity salvaUsuario(@Validated @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = service.conveter(usuarioDTO);
        
        try {
            Usuario usuarioSalvo = service.salvar(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
}
