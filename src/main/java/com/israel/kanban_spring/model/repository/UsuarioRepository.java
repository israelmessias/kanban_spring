package com.israel.kanban_spring.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.israel.kanban_spring.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
}
