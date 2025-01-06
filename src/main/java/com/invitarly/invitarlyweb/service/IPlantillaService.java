package com.invitarly.invitarlyweb.service;

import com.invitarly.invitarlyweb.model.Plantilla;

import java.util.List;

public interface IPlantillaService {

    List<Plantilla> obtenerPlantillas();
    Plantilla obtenerPlantilla(Long id);
    Plantilla crearPlantilla(Plantilla plantilla);
    void borrarPlantilla(Long id);
}
