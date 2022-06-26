package com.israel.kanban_spring.service.interfaces;

import java.util.Optional;

import com.israel.kanban_spring.model.entity.NivelAcesso;
import com.israel.kanban_spring.model.enums.NivelAcessoEnum;

public interface NivelAcessoService {
    Optional<NivelAcesso> obterPorId(Integer id);
    Optional<NivelAcesso> obterPorSigla(NivelAcessoEnum sigla);
}
