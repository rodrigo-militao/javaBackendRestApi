package com.rodrigomilitaobackend.restapi.model;

import java.sql.Timestamp;

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
@Table(name="token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @NonNull
    @Column(name = "token", nullable = false)
    private String token;

    @NonNull
    @Column(name = "login", nullable = false)
    private String login;

    @NonNull
    @Column(name = "expiracao", nullable = false)
    private Timestamp expiracao;

    @NonNull
    @Column(name = "administrador", nullable = false)
    private Boolean administrador;

    
}
