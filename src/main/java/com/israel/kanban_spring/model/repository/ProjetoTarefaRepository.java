package com.israel.kanban_spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.israel.kanban_spring.model.entity.ProjetoTarefa;

public interface ProjetoTarefaRepository extends JpaRepository<ProjetoTarefa, Integer> {
    
}
