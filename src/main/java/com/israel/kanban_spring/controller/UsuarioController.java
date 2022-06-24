package com.israel.kanban_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israel.kanban_spring.Service.impl.UsuarioServiceImpl;
import com.israel.kanban_spring.model.dto.UsuarioDTO;
import com.israel.kanban_spring.model.entity.Usuario;

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

    @PutMapping(path = "{id}")
    public ResponseEntity atualizar(@PathVariable("id") Integer id, @RequestBody UsuarioDTO usuarioDTO) {
    	return (ResponseEntity) service.obterPorId(id).map(entity ->
        {
            try {
                Usuario usuario = service.conveter(usuarioDTO);
                usuario.setId(entity.getId());
                service.atualizar(usuario);
                return new ResponseEntity(usuario, HttpStatus.CREATED);
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("!");
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Não encontrado na base de dados", HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto){
        try {
          Usuario  usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            System.out.println("passou");
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("menssagem: "+e.getMessage());
        }

    }

}
