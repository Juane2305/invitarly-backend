package com.invitarly.invitarlyweb.controller;

import com.invitarly.invitarlyweb.model.Invitacion;
import com.invitarly.invitarlyweb.service.InvitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invitaciones")
public class InvitacionController {

    @Autowired
    private InvitacionService invitacionService;

    // Crear invitación (POST)
    @PostMapping
    public ResponseEntity<Invitacion> crearInvitacion(@RequestBody Invitacion invitacion) {
        Invitacion nueva = invitacionService.crearInvitacion(invitacion);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // Obtener todas las invitaciones (GET)
    @GetMapping
    public ResponseEntity<List<Invitacion>> obtenerTodas() {
        List<Invitacion> lista = invitacionService.obtenerTodas();
        return ResponseEntity.ok(lista);
    }

    // Obtener invitación por ID (GET)
    @GetMapping("/id/{id}")
    public ResponseEntity<Invitacion> obtenerPorId(@PathVariable Long id) {
        Invitacion invitacion = invitacionService.obtenerInvitacion(id);
        if (invitacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invitacion);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Invitacion> obtenerPorUrlPersonalizada(@PathVariable String slug) {
        Invitacion invitacion = invitacionService.obtenerInvitacionPorUrlPersonalizada(slug);
        if (invitacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invitacion);
    }

    // Actualizar invitación (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Invitacion> actualizarInvitacion(
            @PathVariable Long id,
            @RequestBody Invitacion datosNuevos) {

        Invitacion actualizada = invitacionService.actualizarInvitacion(id, datosNuevos);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar invitación (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInvitacion(@PathVariable Long id) {
        boolean eliminado = invitacionService.eliminarInvitacion(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
