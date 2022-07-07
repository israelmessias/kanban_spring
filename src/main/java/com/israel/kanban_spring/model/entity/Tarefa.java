package com.israel.kanban_spring.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    @JsonManagedReference
    @OneToOne(cascade={
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    }, targetEntity = Projeto.class)
    @JoinTable(name="projeto_tarefa",
            joinColumns= @JoinColumn(name="id_tarefa"),
            inverseJoinColumns=@JoinColumn(name="id_projeto"))
    private Projeto projeto;
}
