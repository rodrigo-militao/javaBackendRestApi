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
@Table(name="pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NonNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NonNull
    @Column(name = "sigla", nullable = false)
    private String sigla;

    @NonNull
    @Column(name = "gentilico", nullable = false)
    private String gentilico;

    
}
