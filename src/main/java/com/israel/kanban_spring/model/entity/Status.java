package com.israel.kanban_spring.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
}
