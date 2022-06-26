package com.israel.kanban_spring.service.impl;

import com.israel.kanban_spring.error.ProjetoErro;
import com.israel.kanban_spring.service.interfaces.ProjetoService;
import com.israel.kanban_spring.model.dto.ProjetoDTO;
import com.israel.kanban_spring.model.entity.Projeto;
import com.israel.kanban_spring.model.entity.Tarefa;
import com.israel.kanban_spring.model.repository.ProjetoRepository;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private TarefaServiceImpl serviceTarefa;

    @Override
    public Projeto salvar(Projeto projeto) {

        try {
            Projeto projetoSalvo = repository.saveAndFlush(projeto);
            return projetoSalvo;
        } catch (Exception e) {
            throw new ProjetoErro("Não foi possivel Salvar o Projeto.");
        }
    }

    @Override
    public Projeto atualizar(Projeto projeto) {
        try {
            Objects.requireNonNull(projeto.getId());

            return repository.saveAndFlush(projeto);
        } catch (Exception e) {
            throw new ProjetoErro("Não foi possivel Atualizar o Projeto.");
        }
    }

    @Override
    public Optional<Projeto> obterPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Projeto converter(ProjetoDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setId(dto.getId());
        projeto.setDescricao(dto.getDescricao());
        projeto.setTitulo(dto.getTitulo());

        Set<Tarefa> tarefas = serviceTarefa.obterTarefas(dto.getTarefas());
        projeto.setTarefas(tarefas);

        return projeto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projeto> buscar(Projeto projetoFiltro) {
        Example example = Example.of(projetoFiltro,
                ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }
}
