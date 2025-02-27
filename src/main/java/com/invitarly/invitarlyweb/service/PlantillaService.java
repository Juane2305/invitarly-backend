package com.invitarly.invitarlyweb.service;


import com.invitarly.invitarlyweb.model.Plantilla;
import com.invitarly.invitarlyweb.repository.PlanRepository;
import com.invitarly.invitarlyweb.repository.PlantillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantillaService {

    @Autowired
    private PlantillaRepository plantillaRepository;

    @Autowired
    private PlanRepository planRepository;

    public List<Plantilla> obtenerPlantillas() {
        return plantillaRepository.findAll();
    }


    public Plantilla guardarPlantilla(Plantilla plantilla) {
        return plantillaRepository.save(plantilla);
    }

    public Plantilla obtenerPlantillaPorId(Long id) {
        return plantillaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plantilla no encontrada con id: " + id));
    }

    public void eliminarPlantilla(Long id) {
        plantillaRepository.deleteById(id);
    }

    public Plantilla obtenerPlantillaPorNombre(String nombre) {
        return plantillaRepository.findByNombre(nombre);
    }
}