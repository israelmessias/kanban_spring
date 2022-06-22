package com.israel.kanban_spring.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.Fetch;

import org.checkerframework.checker.units.qual.C;

import lombok.Data;

@Data
@Table(name = "tarefa")
@Entity
public class Tarefa {
    @SequenceGenerator(name = "Tarefa", sequenceName ="tarefa_seq", initialValue = 1, allocationSize =  1)
    @Id
    @GeneratedValue(generator = "Tarefa")
    @Column(name = "id_tarefa")
    private Integer id;

    @Column(name = "titulo_tarefa")
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status")
    private Status status;

    @Column(name = "descricao_tarefa")
    private String descricao;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "previsao_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date previsaoInicial;

    @Column(name = "previsao_final")
    @Temporal(TemporalType.TIMESTAMP)
    private Date privesiaoFinal;
}
