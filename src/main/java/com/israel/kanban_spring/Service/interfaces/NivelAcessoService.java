package com.israel.kanban_spring.Service.interfaces;

import java.util.Optional;

import com.israel.kanban_spring.model.entity.NivelAcesso;

public interface NivelAcessoService {
    Optional<NivelAcesso> obterPorId(Integer id);
}
