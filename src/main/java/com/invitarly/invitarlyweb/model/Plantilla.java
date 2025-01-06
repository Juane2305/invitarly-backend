package com.invitarly.invitarlyweb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Plantilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String urlVistaPrevia;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "plantilla_id")
    private List<Funcion> funciones;
    private String categoria;
}
