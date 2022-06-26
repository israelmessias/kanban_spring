package com.israel.kanban_spring.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

@Data
@Getter
@Table(name = "usuario")
@Entity
public class Usuario {

    public Usuario() {
	}

	@SequenceGenerator(name = "Usuario", sequenceName = "usuario_seq", initialValue = 1, allocationSize = 1)
    @Id
    @GeneratedValue(generator = "Usuario")
    @Column(name="id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_acesso")
    private NivelAcesso nivelAcesso;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "data_user")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

}
