package com.israel.kanban_spring.model.repository;

import com.israel.kanban_spring.model.enums.NivelAcessoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.israel.kanban_spring.model.entity.NivelAcesso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NivelAcessoRepository extends JpaRepository<NivelAcesso, Integer> {

    @Query("SELECT ne FROM NivelAcesso ne WHERE ne.nivelAcessoEnum = :nivelAcessoenum")
    Optional<NivelAcesso> findByNivelAcessoEnum(@Param("nivelAcessoenum") NivelAcessoEnum nivelAcessoEnum);
}
