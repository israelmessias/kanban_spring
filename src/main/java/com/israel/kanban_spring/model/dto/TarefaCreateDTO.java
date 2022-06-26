package com.israel.kanban_spring.model.dto;

import com.israel.kanban_spring.model.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

@Data
public class TarefaCreateDTO {
    private Integer id;

    private String titulo;

    private Integer usuario;

    private String descricao;

    private Date createDate;

    private Date updateDate;

    private Date previsaoInicial;

    private Date privesiaoFinal;

    private Integer projeto;
}
