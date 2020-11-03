package com.rodrigomilitaobackend.restapi.repository;

import com.rodrigomilitaobackend.restapi.model.Token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);
}
