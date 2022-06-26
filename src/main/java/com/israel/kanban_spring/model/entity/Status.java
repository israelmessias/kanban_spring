package com.israel.kanban_spring.model.entity;

import java.util.Date;

import javax.persistence.*;

import com.israel.kanban_spring.model.enums.StatusEnum;
import lombok.Data;

@Data
@Table(name="status")
@Entity
public class Status {
    @SequenceGenerator(name = "Status", sequenceName = "status_seq", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(generator = "Status")
    @Column(name="id_status", unique = true, nullable = false)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sigla")
    private StatusEnum statusEnum;
}
