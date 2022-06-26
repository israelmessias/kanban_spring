package com.israel.kanban_spring.model.dto;

import com.israel.kanban_spring.model.enums.StatusEnum;
import lombok.Data;

import java.util.Date;

@Data
public class TarefaDTO {
    private Integer id;

    private String titulo;

    private Integer usuario;

    private StatusEnum status;

    private String descricao;

    private Date createDate;

    private Date updateDate;

    private Date previsaoInicial;

    private Date privesiaoFinal;

    private Integer projeto;
}
