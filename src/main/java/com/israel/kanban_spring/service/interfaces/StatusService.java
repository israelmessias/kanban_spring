package com.israel.kanban_spring.service.interfaces;

import com.israel.kanban_spring.model.entity.Status;
import com.israel.kanban_spring.model.enums.StatusEnum;

import java.util.Optional;

public interface StatusService {
    Optional<Status>obterStatusPorId(Integer id);
    Optional<Status>obterStatusPorSigla(StatusEnum sigla);

}
