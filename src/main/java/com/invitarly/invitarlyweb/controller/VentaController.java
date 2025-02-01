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

    // GET /api/ventas -> retorna todas las ventas
    @GetMapping
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    // POST /api/ventas -> crear una venta nueva
    @PostMapping
    public Venta crearVenta(@RequestBody Map<String, String> body) {
        // Ejemplo: body { "clienteNombre": "Juan Perez", "estado": "EN_PROCESO" }
        String clienteNombre = body.get("clienteNombre");
        String estado = body.get("estado");

        Venta nuevaVenta = new Venta(clienteNombre, estado);
        return ventaRepository.save(nuevaVenta);
    }

    // DELETE /api/ventas/{id} -> eliminar una venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        if (!ventaRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no existe
        }
        ventaRepository.deleteById(id); // Elimina la venta
        return ResponseEntity.noContent().build(); // Retorna 204 No Content si todo va bien
    }

    // GET /api/ventas/{id} -> retorna una venta por su id
    @GetMapping("/{id}")
    public Venta obtenerVenta(@PathVariable Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    // PUT /api/ventas/{id}/estado -> actualizar el estado de una venta existente
    @PutMapping("/{id}/estado")
    public ResponseEntity<Venta> actualizarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        // body: { "estado": "ENTREGADO" }
        String nuevoEstado = body.get("estado");

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.setEstado(nuevoEstado);
        ventaRepository.save(venta);

        return ResponseEntity.ok(venta);
    }
}
