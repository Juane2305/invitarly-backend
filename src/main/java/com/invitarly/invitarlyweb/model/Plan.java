package com.invitarly.invitarlyweb.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String nombreVisual;

    private String descripcion;

    private String imagen;

    private Double precio;

    @ElementCollection
    private List<String> funcionalidades;

    private String tipoEvento;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreVisual() {
        return nombreVisual;
    }

    public void setNombreVisual(String nombreVisual) {
        this.nombreVisual = nombreVisual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<String> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<String> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
}



