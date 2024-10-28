
package com.practica1.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="arbol")
public class Arbol implements Serializable{
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    @Column(name ="nombre_comun")
    private String nombreComun;
    @Column(name ="tipo_flor")
    private String tipoFlor;
    @Column(name ="dureza_madera")
    private String durezaMadera;
    @Column(name ="altura_promedio")
    private float altura;
    @Column(name ="ruta_imagen")
    private String rutaImagen;

    public Arbol(){
        
    }
    
    
}

