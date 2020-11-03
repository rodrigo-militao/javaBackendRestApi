package com.rodrigomilitaobackend.restapi.service;

import java.util.List;

import javax.transaction.Transactional;

import com.rodrigomilitaobackend.restapi.model.Pais;
import com.rodrigomilitaobackend.restapi.repository.PaisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> findAll(){
        return paisRepository.findAll();
    }

    public Pais savePais(Pais newPais) {
        Pais pais = new Pais();
        pais.setNome(newPais.getNome());
        pais.setSigla(newPais.getSigla());
        pais.setGentilico(newPais.getGentilico());
        return paisRepository.save(pais);
    }

    public Pais updatePais(Pais updatedPais) {
        return paisRepository.save(updatedPais);
    }

    public List<Pais> findByNome(String filtro) {
        return paisRepository.findByNomeContainingIgnoreCase(filtro);
    }
    
    public Boolean deleteById(Long id) {
        paisRepository.deleteById(id);
        return true;
    }
}
