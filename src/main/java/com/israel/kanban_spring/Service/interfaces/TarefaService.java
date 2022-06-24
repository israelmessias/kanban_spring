package com.israel.kanban_spring.Service.interfaces;

import com.israel.kanban_spring.model.entity.Tarefa;

import java.util.List;
import java.util.Set;

public interface TarefaService {
    Set<Tarefa> obterTarefas(Set<Integer> ids);
}
