package com.israel.kanban_spring.model.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ProjetoDTO {
    private Integer id;

    private String titulo;

    private String descricao;

    private Set<Integer> tarefas;
}
