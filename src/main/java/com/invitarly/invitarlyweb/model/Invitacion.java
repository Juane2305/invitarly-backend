package com.invitarly.invitarlyweb.model;

import jakarta.persistence.*;


@Entity
@Table(name = "invitaciones")
public class Invitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_evento")
    private String tipoEvento;
    private String nombreQuinceanera;
    private String tematicaXV;
    private String nombreBebe;
    private String nombrePadres;
    private String padrinos;
    private String novios;
    private String fecha_evento;
    private String fecha_comienzo_calendario;
    private String fecha_fin_calendario;
    private String nombre_iglesia;
    private String nombre_salon;
    private String hora_ceremonia_religiosa;
    private String hora_civil;
    private String hora_evento;
    private String hora_fin_evento;
    private String fecha_cuenta_regresiva;
    private String cbu;
    private String alias;
    private String banco;
    private String cancion;
    private String plantilla_elegida;
    @Column(name = "url_personalizada")
    private String urlPersonalizada;
    @Column(length = 2000)
    private String imagenes;
    private String estado;
    private String plan;
    private String nombre_completo;
    private String linkCeremonia;
    private String linkFiesta;
    private String linkCalendario;
    private String dressCode;
    private String ig_user;
    @Column(length = 2000)
    private String mensaje_personalizado;
    private String link_asistencia;
    private String fecha_tokyo;
    private String fondo;
    private String fondoMobile;

    public Invitacion() {
    }

    public Invitacion(Long id, String tipoEvento, String nombreQuinceanera, String tematicaXV, String nombreBebe, String nombrePadres, String padrinos, String novios, String fecha_evento, String fecha_comienzo_calendario, String fecha_fin_calendario, String nombre_iglesia, String nombre_salon, String hora_ceremonia_religiosa, String hora_civil, String hora_evento, String hora_fin_evento, String fecha_cuenta_regresiva, String cbu, String alias, String banco, String cancion, String plantilla_elegida, String urlPersonalizada, String imagenes, String estado, String plan, String nombre_completo, String linkCeremonia, String linkFiesta, String linkCalendario, String dressCode, String ig_user, String mensaje_personalizado, String link_asistencia, String fecha_tokyo, String fondo, String fondoMobile) {
        this.id = id;
        this.tipoEvento = tipoEvento;
        this.nombreQuinceanera = nombreQuinceanera;
        this.tematicaXV = tematicaXV;
        this.nombreBebe = nombreBebe;
        this.nombrePadres = nombrePadres;
        this.padrinos = padrinos;
        this.novios = novios;
        this.fecha_evento = fecha_evento;
        this.fecha_comienzo_calendario = fecha_comienzo_calendario;
        this.fecha_fin_calendario = fecha_fin_calendario;
        this.nombre_iglesia = nombre_iglesia;
        this.nombre_salon = nombre_salon;
        this.hora_ceremonia_religiosa = hora_ceremonia_religiosa;
        this.hora_civil = hora_civil;
        this.hora_evento = hora_evento;
        this.hora_fin_evento = hora_fin_evento;
        this.fecha_cuenta_regresiva = fecha_cuenta_regresiva;
        this.cbu = cbu;
        this.alias = alias;
        this.banco = banco;
        this.cancion = cancion;
        this.plantilla_elegida = plantilla_elegida;
        this.urlPersonalizada = urlPersonalizada;
        this.imagenes = imagenes;
        this.estado = estado;
        this.plan = plan;
        this.nombre_completo = nombre_completo;
        this.linkCeremonia = linkCeremonia;
        this.linkFiesta = linkFiesta;
        this.linkCalendario = linkCalendario;
        this.dressCode = dressCode;
        this.ig_user = ig_user;
        this.mensaje_personalizado = mensaje_personalizado;
        this.link_asistencia = link_asistencia;
        this.fecha_tokyo = fecha_tokyo;
        this.fondo = fondo;
        this.fondoMobile = fondoMobile;
    }

    public Long getId() {
        return id;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getNombreQuinceanera() {
        return nombreQuinceanera;
    }

    public void setNombreQuinceanera(String nombreQuinceanera) {
        this.nombreQuinceanera = nombreQuinceanera;
    }

    public String getTematicaXV() {
        return tematicaXV;
    }

    public void setTematicaXV(String tematicaXV) {
        this.tematicaXV = tematicaXV;
    }

    public String getNombreBebe() {
        return nombreBebe;
    }

    public void setNombreBebe(String nombreBebe) {
        this.nombreBebe = nombreBebe;
    }

    public String getNombrePadres() {
        return nombrePadres;
    }

    public void setNombrePadres(String nombrePadres) {
        this.nombrePadres = nombrePadres;
    }

    public String getPadrinos() {
        return padrinos;
    }

    public void setPadrinos(String padrinos) {
        this.padrinos = padrinos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNovios() {
        return novios;
    }

    public void setNovios(String novios) {
        this.novios = novios;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public String getFecha_comienzo_calendario() {
        return fecha_comienzo_calendario;
    }

    public void setFecha_comienzo_calendario(String fecha_comienzo_calendario) {
        this.fecha_comienzo_calendario = fecha_comienzo_calendario;
    }

    public String getFecha_fin_calendario() {
        return fecha_fin_calendario;
    }

    public void setFecha_fin_calendario(String fecha_fin_calendario) {
        this.fecha_fin_calendario = fecha_fin_calendario;
    }

    public String getNombre_iglesia() {
        return nombre_iglesia;
    }

    public void setNombre_iglesia(String nombre_iglesia) {
        this.nombre_iglesia = nombre_iglesia;
    }

    public String getNombre_salon() {
        return nombre_salon;
    }

    public void setNombre_salon(String nombre_salon) {
        this.nombre_salon = nombre_salon;
    }

    public String getHora_evento() {
        return hora_evento;
    }

    public void setHora_evento(String hora_evento) {
        this.hora_evento = hora_evento;
    }

    public String getHora_fin_evento() {
        return hora_fin_evento;
    }

    public void setHora_fin_evento(String hora_fin_evento) {
        this.hora_fin_evento = hora_fin_evento;
    }

    public String getHora_ceremonia_religiosa() {
        return hora_ceremonia_religiosa;
    }

    public void setHora_ceremonia_religiosa(String hora_ceremonia_religiosa) {
        this.hora_ceremonia_religiosa = hora_ceremonia_religiosa;
    }

    public String getHora_civil() {
        return hora_civil;
    }

    public void setHora_civil(String hora_civil) {
        this.hora_civil = hora_civil;
    }

    public String getFecha_cuenta_regresiva() {
        return fecha_cuenta_regresiva;
    }

    public void setFecha_cuenta_regresiva(String fecha_cuenta_regresiva) {
        this.fecha_cuenta_regresiva = fecha_cuenta_regresiva;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getPlantilla_elegida() {
        return plantilla_elegida;
    }

    public void setPlantilla_elegida(String plantilla_elegida) {
        this.plantilla_elegida = plantilla_elegida;
    }

    public String getUrlPersonalizada() {
        return urlPersonalizada;
    }

    public void setUrlPersonalizada(String urlPersonalizada) {
        this.urlPersonalizada = urlPersonalizada;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getLinkCeremonia() {
        return linkCeremonia;
    }

    public void setLinkCeremonia(String linkCeremonia) {
        this.linkCeremonia = linkCeremonia;
    }

    public String getLinkFiesta() {
        return linkFiesta;
    }

    public void setLinkFiesta(String linkFiesta) {
        this.linkFiesta = linkFiesta;
    }

    public String getLinkCalendario() {
        return linkCalendario;
    }

    public void setLinkCalendario(String linkCalendario) {
        this.linkCalendario = linkCalendario;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    public String getIg_user() {
        return ig_user;
    }

    public void setIg_user(String ig_user) {
        this.ig_user = ig_user;
    }

    public String getMensaje_personalizado() {
        return mensaje_personalizado;
    }

    public void setMensaje_personalizado(String mensaje_personalizado) {
        this.mensaje_personalizado = mensaje_personalizado;
    }

    public String getLink_asistencia() {
        return link_asistencia;
    }

    public void setLink_asistencia(String link_asistencia) {
        this.link_asistencia = link_asistencia;
    }

    public String getFecha_tokyo() {
        return fecha_tokyo;
    }

    public void setFecha_tokyo(String fecha_tokyo) {
        this.fecha_tokyo = fecha_tokyo;
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public String getFondoMobile() {
        return fondoMobile;
    }

    public void setFondoMobile(String fondo_mobile) {
        this.fondoMobile = fondo_mobile;
    }
}
