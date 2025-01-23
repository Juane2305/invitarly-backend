package com.invitarly.invitarlyweb.service;

import com.invitarly.invitarlyweb.model.Plan;
import com.invitarly.invitarlyweb.model.Plantilla;
import com.invitarly.invitarlyweb.repository.PlanRepository;
import com.invitarly.invitarlyweb.repository.PlantillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlantillaService {

    @Autowired
    private PlantillaRepository plantillaRepository;

    @Autowired
    private PlanRepository planRepository;

    // Obtener todas las plantillas
    public List<Plantilla> obtenerPlantillas() {
        return plantillaRepository.findAll();
    }


    // Guardar una nueva plantilla
    public Plantilla guardarPlantilla(Plantilla plantilla) {
        return plantillaRepository.save(plantilla);
    }

    // Obtener una plantilla por ID
    public Plantilla obtenerPlantillaPorId(Long id) {
        return plantillaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plantilla no encontrada con id: " + id));
    }

    // Eliminar una plantilla
    public void eliminarPlantilla(Long id) {
        plantillaRepository.deleteById(id);
    }

    public Plantilla obtenerPlantillaPorNombre(String nombre) {
        return plantillaRepository.findByNombre(nombre);
    }
}