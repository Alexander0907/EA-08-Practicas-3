
package com.practica1.service.impl;


import com.practica1.dao.ArbolRepository;
import com.practica1.domain.Arbol;
import com.practica1.service.ArbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArbolServiceImpl implements ArbolService {
    @Autowired
    private ArbolRepository arbolRepository;

    @Override
    public List<Arbol> listarArboles() {
        return arbolRepository.findAll();
    }

    @Override
    public Arbol guardarArbol(Arbol arbol) {
        return arbolRepository.save(arbol);
    }

    @Override
    public Arbol obtenerArbolPorId(Long id) {
        return arbolRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarArbol(Long id) {
        arbolRepository.deleteById(id);
    }
}
