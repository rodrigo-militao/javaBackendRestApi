package com.rodrigomilitaobackend.restapi.controller;

import com.rodrigomilitaobackend.restapi.model.UsuarioAutenticado;
import com.rodrigomilitaobackend.restapi.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/autenticar")
    @ResponseBody
    public UsuarioAutenticado authenticateUser(@RequestParam String login, @RequestParam String senha){
        return usuarioService.authenticateUser(login, senha);
    }

    @GetMapping("/renovar-ticket")
    @ResponseBody
    public Boolean reAuthenticateUser(@RequestParam String token){
        return usuarioService.reAuthenticateUser(token);
    }
}
