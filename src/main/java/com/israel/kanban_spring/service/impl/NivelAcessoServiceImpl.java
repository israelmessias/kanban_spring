package com.israel.kanban_spring.service.impl;

import java.util.Optional;

import com.israel.kanban_spring.model.enums.NivelAcessoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.israel.kanban_spring.service.interfaces.NivelAcessoService;
import com.israel.kanban_spring.model.entity.NivelAcesso;
import com.israel.kanban_spring.model.repository.NivelAcessoRepository;

@Service
public class NivelAcessoServiceImpl implements NivelAcessoService {

    @Autowired
    NivelAcessoRepository repository;

    @Override
    public Optional<NivelAcesso> obterPorId(Integer id) {
        // TODO Auto-generated method stub
        return repository.findById(id);
    }

    @Override
    public Optional<NivelAcesso> obterPorSigla(NivelAcessoEnum sigla) {
        return repository.findByNivelAcessoEnum(sigla);
    }

}
