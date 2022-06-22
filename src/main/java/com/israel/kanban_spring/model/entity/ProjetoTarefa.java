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

import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Data
@Table
@Entity
public class ProjetoTarefa {
    @SequenceGenerator(name = "ProjetoTarefa", sequenceName = "projeto_tarefa_sequence", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(generator = "ProjetoTarefa")
    @Column(name="id_projeto_tarefa", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_projeto")
    private Tarefa tarefa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tarefa")
    private Projeto projeto;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
}
