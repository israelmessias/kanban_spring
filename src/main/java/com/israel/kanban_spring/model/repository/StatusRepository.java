package com.israel.kanban_spring.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.israel.kanban_spring.model.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    
}
