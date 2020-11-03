package com.rodrigomilitaobackend.restapi.service;

import javax.transaction.Transactional;

import com.rodrigomilitaobackend.restapi.model.Usuario;
import com.rodrigomilitaobackend.restapi.model.UsuarioAutenticado;
import com.rodrigomilitaobackend.restapi.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    public UsuarioAutenticado authenticateUser(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLogin(login);

        UsuarioAutenticado usuarioAutenticado = new UsuarioAutenticado();

        if(usuario == null) return usuarioAutenticado;
        
        if(usuario.getSenha().equals(senha)) {
            String jwtToken = tokenService.addAuthentication(usuario);

            usuarioAutenticado.setNome(usuario.getNome());
            usuarioAutenticado.setLogin(usuario.getLogin());            
            usuarioAutenticado.setAdministrador(usuario.getAdministrador());
            usuarioAutenticado.setAutenticado(true);
            usuarioAutenticado.setToken(jwtToken);

            return usuarioAutenticado;
        } else {
            usuarioAutenticado.setAutenticado(false);
            return usuarioAutenticado;
        }
    }

    public Boolean reAuthenticateUser(String token) {
        return tokenService.reAuthentication(token);
    }
    
}
