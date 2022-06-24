package com.israel.kanban_spring.Service.interfaces;

import com.israel.kanban_spring.model.entity.Status;

import java.util.Optional;

public interface StatusService {
    Optional<Status>obterStatusPorId(Integer id);
}
