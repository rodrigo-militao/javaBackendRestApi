package com.rodrigomilitaobackend.restapi.repository;

import com.rodrigomilitaobackend.restapi.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
