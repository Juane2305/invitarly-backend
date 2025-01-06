package com.invitarly.invitarlyweb.service;

import com.invitarly.invitarlyweb.model.Plantilla;
import com.invitarly.invitarlyweb.repository.IPlantillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantillaService implements IPlantillaService{

    @Autowired
    private IPlantillaRepository plantillaRepository;

    @Override
    public List<Plantilla> obtenerPlantillas() {
        return plantillaRepository.findAll();
    }

    @Override
    public Plantilla obtenerPlantilla(Long id) {
        return plantillaRepository.findById(id).orElseThrow(() -> new RuntimeException("Plantilla no encontrada"));
    }

    @Override
    public Plantilla crearPlantilla(Plantilla plantilla) {
        return plantillaRepository.save(plantilla);
    }

    @Override
    public void borrarPlantilla(Long id) {
        plantillaRepository.deleteById(id);
    }
}
