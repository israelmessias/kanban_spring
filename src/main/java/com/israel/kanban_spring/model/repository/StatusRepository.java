package com.israel.kanban_spring.model.repository;

import com.israel.kanban_spring.model.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.israel.kanban_spring.model.entity.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query("SELECT status FROM Status status WHERE status.statusEnum = :status")
    Optional<Status> findByStatusSigla(@Param("status") StatusEnum status);
}
