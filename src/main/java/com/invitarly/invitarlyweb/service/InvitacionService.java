package com.invitarly.invitarlyweb.service;

import com.invitarly.invitarlyweb.model.Invitacion;
import com.invitarly.invitarlyweb.repository.InvitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvitacionService {

    @Autowired
    private InvitacionRepository invitacionRepository;

    public Invitacion crearInvitacion(Invitacion invitacion) {
        return invitacionRepository.save(invitacion);
    }

    public Invitacion obtenerInvitacionPorUrlPersonalizada(String urlPersonalizada) {
        return invitacionRepository.findByUrlPersonalizada(urlPersonalizada);
    }

    public Invitacion obtenerInvitacion(Long id) {
        Optional<Invitacion> optInv = invitacionRepository.findById(id);
        return optInv.orElse(null);
    }

    public List<Invitacion> obtenerTodas() {
        return invitacionRepository.findAll();
    }

    public Invitacion actualizarInvitacion(Long id, Invitacion datosNuevos) {
        Optional<Invitacion> optInv = invitacionRepository.findById(id);
        if (optInv.isEmpty()) {
            return null;
        }

        Invitacion invitacionExistente = optInv.get();

        invitacionExistente.setNovios(datosNuevos.getNovios());
        invitacionExistente.setFecha_evento(datosNuevos.getFecha_evento());
        invitacionExistente.setFecha_comienzo_calendario(datosNuevos.getFecha_comienzo_calendario());
        invitacionExistente.setFecha_fin_calendario(datosNuevos.getFecha_fin_calendario());
        invitacionExistente.setNombre_iglesia(datosNuevos.getNombre_iglesia());
        invitacionExistente.setNombre_salon(datosNuevos.getNombre_salon());
        invitacionExistente.setHora_ceremonia_religiosa(datosNuevos.getHora_ceremonia_religiosa());
        invitacionExistente.setHora_civil(datosNuevos.getHora_civil());
        invitacionExistente.setHora_evento(datosNuevos.getHora_evento());
        invitacionExistente.setFecha_cuenta_regresiva(datosNuevos.getFecha_cuenta_regresiva());
        invitacionExistente.setCbu(datosNuevos.getCbu());
        invitacionExistente.setAlias(datosNuevos.getAlias());
        invitacionExistente.setBanco(datosNuevos.getBanco());
        invitacionExistente.setCancion(datosNuevos.getCancion());
        invitacionExistente.setPlantilla_elegida(datosNuevos.getPlantilla_elegida());
        invitacionExistente.setUrlPersonalizada(datosNuevos.getUrlPersonalizada());
        invitacionExistente.setImagenes(datosNuevos.getImagenes());
        invitacionExistente.setEstado(datosNuevos.getEstado());
        invitacionExistente.setPlan(datosNuevos.getPlan());
        invitacionExistente.setNombre_completo(datosNuevos.getNombre_completo());
        invitacionExistente.setLinkCeremonia(datosNuevos.getLinkCeremonia());
        invitacionExistente.setLinkFiesta(datosNuevos.getLinkFiesta());
        invitacionExistente.setLinkCalendario(datosNuevos.getLinkCalendario());
        invitacionExistente.setDressCode(datosNuevos.getDressCode());
        invitacionExistente.setIg_user(datosNuevos.getIg_user());
        invitacionExistente.setMensaje_personalizado(datosNuevos.getMensaje_personalizado());
        invitacionExistente.setLink_asistencia(datosNuevos.getLink_asistencia());
        invitacionExistente.setFecha_tokyo(datosNuevos.getFecha_tokyo());

        return invitacionRepository.save(invitacionExistente);
    }

    public boolean eliminarInvitacion(Long id) {
        if (invitacionRepository.existsById(id)) {
            invitacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
