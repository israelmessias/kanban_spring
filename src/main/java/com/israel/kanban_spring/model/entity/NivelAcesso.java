package com.israel.kanban_spring.model.entity;

import javax.annotation.Generated;
import javax.persistence.*;

import com.israel.kanban_spring.model.enums.NivelAcessoEnum;
import lombok.Data;

@Data
@Table(name = "nivel_acesso")
@Entity
public class NivelAcesso {
    
    @SequenceGenerator(name = "NivelAcesso", sequenceName = "nivel_acesso_seq", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(generator = "NivelAcesso")
    @Column(name="id_nivel_acesso", unique = true, nullable = false)
    private Integer id;
    
    @Column(name="descricao")
    private String descricao;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sigla")
    private NivelAcessoEnum nivelAcessoEnum;
}
