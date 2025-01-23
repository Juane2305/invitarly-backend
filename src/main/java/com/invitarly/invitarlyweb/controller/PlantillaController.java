package com.invitarly.invitarlyweb.controller;

import com.invitarly.invitarlyweb.model.Plantilla;
import com.invitarly.invitarlyweb.service.PlantillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/plantillas")
public class PlantillaController {

    @Autowired
    private PlantillaService plantillaService;

    @GetMapping
    public List<Plantilla> obtenerPlantillas() {
        return plantillaService.obtenerPlantillas();
    }


    @GetMapping("/{nombre}")
    public ResponseEntity<Plantilla> obtenerPlantillaPorNombre(@PathVariable String nombre) {
        Plantilla plantilla = plantillaService.obtenerPlantillaPorNombre(nombre);
        if (plantilla != null) {
            return ResponseEntity.ok(plantilla);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearPlantilla(@RequestBody Plantilla plantilla) {
        try {
            Plantilla nuevaPlantilla = plantillaService.guardarPlantilla(plantilla);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPlantilla);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la plantilla");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPlantilla(@PathVariable Long id) {
        try {
            plantillaService.eliminarPlantilla(id);
            return ResponseEntity.ok("Plantilla eliminada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantilla no encontrada");
        }
    }
}
