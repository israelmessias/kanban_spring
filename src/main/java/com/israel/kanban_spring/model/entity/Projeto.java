package com.israel.kanban_spring.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
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

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="projeto_tarefa",
            joinColumns={@JoinColumn(name="id_projeto",
                    referencedColumnName="id_projeto")},
            inverseJoinColumns={@JoinColumn(name="id_tarefa",
                    referencedColumnName="id_tarefa")})
    Set<Tarefa> tarefas;
}
