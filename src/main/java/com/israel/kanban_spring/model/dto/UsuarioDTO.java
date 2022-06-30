package com.israel.kanban_spring.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.israel.kanban_spring.model.enums.NivelAcessoEnum;

import lombok.Data;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class UsuarioDTO {
  
    private Integer id;

    private String nome;

    private String email;

    private NivelAcessoEnum nivelAcesso;

    private String senha;

    public UsuarioDTO(){}
}
