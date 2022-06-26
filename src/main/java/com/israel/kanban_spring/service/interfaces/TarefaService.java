package com.israel.kanban_spring.service.interfaces;

import com.israel.kanban_spring.model.dto.TarefaCreateDTO;
import com.israel.kanban_spring.model.dto.TarefaUpdateDTO;
import com.israel.kanban_spring.model.entity.Tarefa;
import com.israel.kanban_spring.model.enums.StatusEnum;

import java.util.Set;

public interface TarefaService {
    Set<Tarefa> obterTarefas(Set<Integer> ids);

    Tarefa criar(Tarefa tarefa);

    Tarefa atualizar(Tarefa tarefa);

    void atualizarStatus(Tarefa tarefa, StatusEnum status);

    Tarefa converterUpdate (TarefaUpdateDTO dto);

    Tarefa converterCreate(TarefaCreateDTO dto);

}
