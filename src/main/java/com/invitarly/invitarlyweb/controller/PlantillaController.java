package com.invitarly.invitarlyweb.controller;

import com.invitarly.invitarlyweb.model.Plantilla;
import com.invitarly.invitarlyweb.service.IPlantillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/plantillas")
@RestController
public class PlantillaController {

        @Autowired
        private IPlantillaService plantillaService;

        // Obtener todas las plantillas
        @GetMapping
        public List<Plantilla> obtenerPlantillas() {
            return plantillaService.obtenerPlantillas();
        }

        // Obtener plantilla por ID
        @GetMapping("/{id}")
        public Plantilla obtenerPlantilla(@PathVariable Long id) {
            return plantillaService.obtenerPlantilla(id);
        }

        // Crear una nueva plantilla
        @PostMapping
        public ResponseEntity<Plantilla> crearPlantilla(@RequestBody Plantilla plantilla) {
                System.out.println("Plantilla recibida: " + plantilla);
                plantilla.getFunciones().forEach(funcion -> System.out.println("Funcion recibida: " + funcion));
                Plantilla nuevaPlantilla = plantillaService.crearPlantilla(plantilla);
                return ResponseEntity.ok(nuevaPlantilla);
        }

        // Borrar plantilla por ID
        @DeleteMapping("/{id}")
        public void borrarPlantilla(@PathVariable Long id) {
            plantillaService.borrarPlantilla(id);
        }
}
