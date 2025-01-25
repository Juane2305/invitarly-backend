package com.invitarly.invitarlyweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @Autowired
    private StripeService stripeService;

    @PostMapping("/{planNombre}")
    public ResponseEntity<?> crearPago(@PathVariable String planNombre, @RequestParam String pais) {
        try {
            if (pais.equalsIgnoreCase("Argentina")) {
                // Crear preferencia de pago con Mercado Pago
                String urlPago = mercadoPagoService.crearPreferencia(planNombre);
                return ResponseEntity.ok(urlPago);
            } else {
                // Crear sesión de pago con Stripe
                String urlPago = stripeService.crearSesion(planNombre);
                return ResponseEntity.ok(urlPago);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el pago.");
        }
    }
}
