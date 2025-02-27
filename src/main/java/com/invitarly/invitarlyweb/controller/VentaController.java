package com.invitarly.invitarlyweb.controller;

import com.invitarly.invitarlyweb.model.Venta;
import com.invitarly.invitarlyweb.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @PostMapping
    public Venta crearVenta(@RequestBody Map<String, String> body) {
        String clienteNombre = body.get("clienteNombre");
        String estado = body.get("estado");

        Venta nuevaVenta = new Venta(clienteNombre, estado);
        return ventaRepository.save(nuevaVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        if (!ventaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ventaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Venta obtenerVenta(@PathVariable Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Venta> actualizarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        String nuevoEstado = body.get("estado");

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.setEstado(nuevoEstado);
        ventaRepository.save(venta);

        return ResponseEntity.ok(venta);
    }
}
