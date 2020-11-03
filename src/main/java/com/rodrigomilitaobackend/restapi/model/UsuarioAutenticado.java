package com.rodrigomilitaobackend.restapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioAutenticado {

    private String token = "error";

    private String login;

    private String nome;

    private Boolean administrador;

    private Boolean autenticado = false;
    
}
