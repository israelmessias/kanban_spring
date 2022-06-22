package com.israel.kanban_spring.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class UsuarioDTO {
  
    private Integer id;

    private String nome;

    private String email;

    private Integer nivelAcesso;

    private String senha;

    public UsuarioDTO(){}
}
