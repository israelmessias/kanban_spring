package com.israel.kanban_spring.Service.impl;

import com.israel.kanban_spring.Service.interfaces.StatusService;
import com.israel.kanban_spring.model.entity.Status;
import com.israel.kanban_spring.model.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusRepository repository;
    @Override
    public Optional<Status> obterStatusPorId(Integer id) {
        return repository.findById(id);
    }
}
