package com.israel.kanban_spring.model.repository;

import com.israel.kanban_spring.model.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    
	@Query("SELECT tf FROM Tarefa tf where tf.projeto = :projeto order by tf.status")
	List<Tarefa> findByIdTarefa(Integer id);
}
