package com.invitarly.invitarlyweb.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datos básicos de la venta
    private String clienteNombre;   // Nombre del cliente/comprador
    private String estado;         // EN_PROCESO, ENTREGADO, CANCELADO, etc.

    private LocalDateTime fechaCreacion;

    public Venta() {
    }

    public Venta(String clienteNombre, String estado) {
        this.clienteNombre = clienteNombre;
        this.estado = estado;
        this.fechaCreacion = LocalDateTime.now();
    }

    // GETTERS y SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}