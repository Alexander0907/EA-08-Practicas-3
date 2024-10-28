
package com.practica1.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="arbol")
public class Arbol implements Serializable{
    
    private static final long SerialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    private String nombreComun;
    private String tipoFlor;
    private String durezaMadera;
    private float altura;
    private String rutaImagen;

    public Arbol(){
        
    }
    
    
}

