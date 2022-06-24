package com.israel.kanban_spring.Service.impl;

import com.israel.kanban_spring.Service.interfaces.TarefaService;
import com.israel.kanban_spring.model.entity.Tarefa;
import com.israel.kanban_spring.model.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    TarefaRepository repository;

    @Override
    public Set<Tarefa> obterTarefas(Set<Integer> ids) {

        List<Tarefa> tarefas = null;
        ids = new HashSet<>();
        try {
            if(ids.isEmpty()){
              tarefas = repository.findAll();
            }else{
                tarefas = repository.findAllById(ids);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Set tarefaSet = Set.copyOf(tarefas);

      return tarefaSet;
    }
}
