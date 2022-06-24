package com.israel.kanban_spring.controller;

import com.israel.kanban_spring.Service.impl.ProjetoServiceImpl;
import com.israel.kanban_spring.model.dto.ProjetoDTO;
import com.israel.kanban_spring.model.entity.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    ProjetoServiceImpl service;

    @GetMapping
    public String hello(){
        return "Projeto inicializado";
    }

    @PostMapping
    public ResponseEntity salvarProjeto(@RequestBody ProjetoDTO projetoDTO){

        try {
            Projeto projeto = service.converter(projetoDTO);
            System.out.println(projeto.getDescricao());
            service.salvar(projeto);
            return new ResponseEntity(projeto, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("{id}")
    public ResponseEntity atualizarProjeto(@PathVariable Integer id, @RequestBody ProjetoDTO dto){
        return (ResponseEntity) service.obterPorId(id).map(entity ->{
            try {
                Projeto projeto = service.converter(dto);
                projeto.setId(entity.getId());
                service.atualizar(projeto);
                return new ResponseEntity(projeto, HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }


        }).orElseGet( () -> new ResponseEntity("Projeto nao encontrado.", HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/buscar")
    public ResponseEntity buscar(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "titulo", required = false) String titulo
    )
    {
        Projeto projetoFiltro = new Projeto();
        projetoFiltro.setTitulo(titulo);
        projetoFiltro.setDescricao(descricao);

        List<Projeto> projetos = service.buscar(projetoFiltro);
      return ResponseEntity.ok(projetos);
    }
}
