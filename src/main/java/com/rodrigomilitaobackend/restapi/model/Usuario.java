package com.rodrigomilitaobackend.restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @NonNull
    @Column(name = "login", nullable = false)
    private String login;

    @NonNull
    @Column(name = "senha", nullable = false)
    private String senha;

    @NonNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NonNull
    @Column(name = "administrador", nullable = false)
    private Boolean administrador;
    
}
