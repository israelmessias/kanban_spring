package com.israel.kanban_spring.model.repository;

import java.util.Optional;

import com.israel.kanban_spring.model.entity.NivelAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import com.israel.kanban_spring.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("SELECT usu FROM Usuario usu where usu.nivelAcesso = :nivelAcesso")
    Optional<Usuario> findByUsuarioByNivelAceso(@Param("nivelAcesso")NivelAcesso nivelAcesso);
}
