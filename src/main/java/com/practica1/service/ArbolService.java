
package com.practica1.service;

import com.practica1.domain.Arbol;
import java.util.List;

public interface ArbolService {
    List<Arbol> listarArboles();
    Arbol guardarArbol(Arbol arbol);
    Arbol obtenerArbolPorId(Long id);
    void eliminarArbol(Long id);
}
