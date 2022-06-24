package com.israel.kanban_spring.Service.interfaces;

import com.israel.kanban_spring.model.dto.ProjetoDTO;
import com.israel.kanban_spring.model.entity.Projeto;

import java.util.List;
import java.util.Optional;

public interface ProjetoService {
    Projeto salvar(Projeto projeto);

    Projeto atualizar(Projeto projeto);

    Optional<Projeto> obterPorId(Integer id);

    Projeto converter(ProjetoDTO dto);

    List<Projeto> buscar(Projeto projetoFiltro);
}
