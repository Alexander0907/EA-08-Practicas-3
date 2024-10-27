
package com.practica1.contoller;


import com.practica1.domain.Arbol;
import com.practica1.service.ArbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/arboles")
public class ArbolController {
    @Autowired
    private ArbolService arbolService;

    @GetMapping
    public String listarArboles(Model model) {
        model.addAttribute("arboles", arbolService.listarArboles());
        return "arboles";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("arbol", new Arbol());
        return "formulario_arbol";
    }

    @PostMapping
    public String guardarArbol(@ModelAttribute Arbol arbol) {
        arbolService.guardarArbol(arbol);
        return "redirect:/arboles";
    }

    @GetMapping("/editar/{id}")
    public String editarArbol(@PathVariable Long id, Model model) {
        Arbol arbol = arbolService.obtenerArbolPorId(id);
        model.addAttribute("arbol", arbol);
        return "formulario_arbol";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarArbol(@PathVariable Long id) {
        arbolService.eliminarArbol(id);
        return "redirect:/arboles";
    }
}

