package com.rodrigomilitaobackend.restapi.controller;

import java.util.List;

import com.rodrigomilitaobackend.restapi.model.Pais;
import com.rodrigomilitaobackend.restapi.service.PaisService;
import com.rodrigomilitaobackend.restapi.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/listar")
    @ResponseBody
    public List<Pais> listAll(@RequestParam String token){
        tokenService.verifyAuthentication(token, false);
        return paisService.findAll();
    }

    @PostMapping("/salvar")
    @ResponseBody
    public Pais saveOne(@RequestBody Pais requestPais, @RequestParam String token){
        tokenService.verifyAuthentication(token, false);

        if(requestPais.getId() == 0) return paisService.savePais(requestPais);
        else return paisService.updatePais(requestPais);
    }

    @GetMapping("/pesquisar")
    @ResponseBody
    public List<Pais> search(@RequestParam String filtro, @RequestParam String token){
        tokenService.verifyAuthentication(token, false);
        return paisService.findByNome(filtro);
    }

    @GetMapping("/excluir")
    public Boolean delete(@RequestParam Long id, @RequestParam String token){
        tokenService.verifyAuthentication(token, true);
        return paisService.deleteById(id);
    }

}
