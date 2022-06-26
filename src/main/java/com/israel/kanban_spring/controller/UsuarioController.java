package com.israel.kanban_spring.controller;

import com.israel.kanban_spring.model.dto.NivelAcessoDTO;
import com.israel.kanban_spring.model.dto.UsuarioUpdateDTO;
import com.israel.kanban_spring.model.enums.NivelAcessoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.israel.kanban_spring.service.impl.UsuarioServiceImpl;
import com.israel.kanban_spring.model.dto.UsuarioDTO;
import com.israel.kanban_spring.model.entity.Usuario;

import java.util.Optional;

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

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Integer id, @RequestBody UsuarioUpdateDTO usuarioDTO) {
    	return (ResponseEntity) service.obterPorId(id).map(entity ->
        {
            try {
                Usuario usuario = service.conveterUpdate(usuarioDTO);
                usuario.setId(entity.getId());
                usuario.setNivelAcesso(entity.getNivelAcesso());
                service.atualizar(usuario);
                return new ResponseEntity(usuario, HttpStatus.CREATED);
            } catch (Exception e) {
                //TODO: handle exception

                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Não encontrado na base de dados", HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto){
        try {
          Usuario  usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());

            return ResponseEntity.ok(usuarioAutenticado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("menssagem: "+e.getMessage());
        }

    }

    @PutMapping("/atualizar/acesso")
    public ResponseEntity atualizarNiveldeAcesso( @RequestBody NivelAcessoDTO nivelAcesso){
        return (ResponseEntity) service.obterPorId(nivelAcesso.getScrumMaster()).map(entity ->{
            try {
                Optional<Usuario> scrum = service.obterPorId(entity.getId());

                if (scrum.get().getNivelAcesso().getNivelAcessoEnum().equals(NivelAcessoEnum.SM)) {
                    Usuario  nUsuario = service.atualizarNivelAcesso(scrum.get(),
                            nivelAcesso.getUsuario(), nivelAcesso.getNivelAcessoEnum());
                    return new  ResponseEntity(nUsuario, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity("Somente Scrum Master pode fazer essa requisição",
                            HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet( () -> new ResponseEntity("Id não encontrado.", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletarUsuario(@PathVariable Integer id){
        return service.obterPorId(id).map(entity ->
        {
            service.deletar(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(
                () -> new ResponseEntity("Usuario nao encontrado na base de dados",
                        HttpStatus.BAD_REQUEST));
    }

}
