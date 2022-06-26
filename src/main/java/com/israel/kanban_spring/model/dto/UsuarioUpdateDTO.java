package com.israel.kanban_spring.model.dto;

import com.israel.kanban_spring.model.enums.NivelAcessoEnum;
import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private Integer id;

    private String nome;

    private String email;

    private String senha;
}
