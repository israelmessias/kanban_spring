package com.israel.kanban_spring.model.dto;

import com.israel.kanban_spring.model.enums.NivelAcessoEnum;
import lombok.Data;

@Data
public class NivelAcessoDTO {
    private Integer scrumMaster;
    private Integer usuario;
    private NivelAcessoEnum nivelAcessoEnum;
}
