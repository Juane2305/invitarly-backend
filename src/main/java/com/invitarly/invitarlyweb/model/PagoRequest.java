package com.invitarly.invitarlyweb.model;

import java.math.BigDecimal;

public class PagoRequest {

    // Constructor vacío para la deserialización de Jackson
    public PagoRequest() {
    }

    private String titulo;
    private String descripcion;
    private int cantidad;
    private BigDecimal precioUnitario;

    // Datos del cliente
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    // Datos personalizados de la plantilla
    private String novios; // Nombres de los novios
    private String fechaHora; // Fecha y hora en formato String
    private String datosBancarios; // Datos bancarios
    private String dressCode; // Dress Code
    private String mensaje; // Mensaje para los invitados
    private String linkEvento; // Link de Google Maps del evento
    private String linkCeremonia; // Link de Google Maps de la ceremonia
    private String cancion; // Nombre o link de la canción
    private String instagramWall; // Link al perfil de Instagram
    private String comentariosAdicionales; // Cualquier detalle extra

    // Getters y Setters de todos los campos

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNovios() {
        return novios;
    }

    public void setNovios(String novios) {
        this.novios = novios;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDatosBancarios() {
        return datosBancarios;
    }

    public void setDatosBancarios(String datosBancarios) {
        this.datosBancarios = datosBancarios;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getLinkEvento() {
        return linkEvento;
    }

    public void setLinkEvento(String linkEvento) {
        this.linkEvento = linkEvento;
    }

    public String getLinkCeremonia() {
        return linkCeremonia;
    }

    public void setLinkCeremonia(String linkCeremonia) {
        this.linkCeremonia = linkCeremonia;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getInstagramWall() {
        return instagramWall;
    }

    public void setInstagramWall(String instagramWall) {
        this.instagramWall = instagramWall;
    }

    public String getComentariosAdicionales() {
        return comentariosAdicionales;
    }

    public void setComentariosAdicionales(String comentariosAdicionales) {
        this.comentariosAdicionales = comentariosAdicionales;
    }
}