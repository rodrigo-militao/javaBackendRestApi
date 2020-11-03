package com.rodrigomilitaobackend.restapi.repository;

import java.util.List;

import com.rodrigomilitaobackend.restapi.model.Pais;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PaisRepository extends JpaRepository<Pais, Long> {
    List<Pais> findByNomeContainingIgnoreCase(String filtro);
}
