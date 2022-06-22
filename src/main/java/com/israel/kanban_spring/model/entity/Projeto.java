package com.israel.kanban_spring.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Projeto {
    @SequenceGenerator(name = "Projeto", sequenceName = "projeto_seq", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(generator = "Projeto")
    @Column(name="id_projeto", unique = true, nullable = false)
    private Integer id;

    @Column(name = "titulo_projeto")
    private String titulo;

    @Column(name = "descricao_projeto")
    private String descricao;
}
