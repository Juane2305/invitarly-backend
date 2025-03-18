package com.invitarly.invitarlyweb.controller;

import com.invitarly.invitarlyweb.model.PagoRequest;
import com.invitarly.invitarlyweb.model.Venta;
import com.invitarly.invitarlyweb.repository.VentaRepository;
import com.invitarly.invitarlyweb.service.EmailService;
import com.invitarly.invitarlyweb.util.PlanPlantillaPrecio;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "http://localhost:5173")
public class PagoController {

    private static final Logger logger = LoggerFactory.getLogger(PagoController.class);

    private final EmailService emailService;

    @Autowired
    private VentaRepository ventaRepository;

    @Value("${invitarly.notificacion.email}")
    private String correoPropietario;

    public PagoController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/crear-preferencia")
    public ResponseEntity<String> crearPreferencia(
            @RequestParam String plan,
            @RequestParam String plantilla,
            @RequestBody PagoRequest request
    ) {
        try {
            logger.info("=== [crear-preferencia] Se recibió el Request con los siguientes datos ===");
            logger.info("Plan: {}, Plantilla: {}", plan, plantilla);
            logger.info("Nombre: {}, Apellido: {}, Email: {}, Telefono: {}",
                    request.getNombre(), request.getApellido(), request.getEmail(), request.getTelefono());
            logger.info("Novios: {}, FechaHora: {}, DatosBancarios: {}, DressCode: {}, Mensaje: {}",
                    request.getNovios(),
                    request.getFechaHora(),
                    request.getDatosBancarios(),
                    request.getDressCode(),
                    request.getMensaje());
            logger.info("LinkEvento: {}, LinkCeremonia: {}, Cancion: {}, InstagramWall: {}, ComentariosAdicionales: {}",
                    request.getLinkEvento(),
                    request.getLinkCeremonia(),
                    request.getCancion(),
                    request.getInstagramWall(),
                    request.getComentariosAdicionales());

            if (plan.isEmpty() || plantilla.isEmpty()) {
                return ResponseEntity.badRequest().body("El plan o la plantilla no pueden estar vacíos.");
            }

            Double precio = PlanPlantillaPrecio.getPrecio(plan, plantilla);
            if (precio == null || precio <= 0.0) {
                return ResponseEntity.badRequest().body("Combinación de plan y plantilla no válida.");
            }

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .title("Plantilla: " + plantilla + " | Plan: " + plan)
                    .description(request.getDescripcion() != null
                            ? request.getDescripcion()
                            : "Invitaciones Digitales Personalizadas")
                    .quantity(1)
                    .unitPrice(BigDecimal.valueOf(precio))
                    .currencyId("ARS")
                    .build();

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("http://www.invitarly.com/pago-exitoso")
                    .failure("http://www.invitarly.com/pago-fallido")
                    .pending("http://www.invitarly.com/pago-pendiente")
                    .build();

            PreferencePayerRequest payer = PreferencePayerRequest.builder()
                    .name(request.getNombre())
                    .surname(request.getApellido())
                    .email(request.getEmail())
                    .build();

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("plan", plan);
            metadata.put("plantilla", plantilla);
            metadata.put("nombre", request.getNombre());
            metadata.put("apellido", request.getApellido());
            metadata.put("email", request.getEmail());
            metadata.put("telefono", request.getTelefono());
            metadata.put("descripcion", request.getDescripcion());
            metadata.put("novios", request.getNovios());
            metadata.put("fechaHora", request.getFechaHora());
            metadata.put("datosBancarios", request.getDatosBancarios());
            metadata.put("dressCode", request.getDressCode());
            metadata.put("mensaje", request.getMensaje());
            metadata.put("cancion", request.getCancion());
            metadata.put("instagramWall", request.getInstagramWall());
            metadata.put("linkEvento", request.getLinkEvento());
            metadata.put("linkCeremonia", request.getLinkCeremonia());
            metadata.put("comentariosAdicionales", request.getComentariosAdicionales());

            logger.info("Metadata generada: {}", metadata);

            Venta venta = new Venta(request.getNombre(), "EN_PROCESO");
            venta = ventaRepository.save(venta);

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(Collections.singletonList(itemRequest))
                    .backUrls(backUrls)
                    .payer(payer)
                    .autoReturn("approved")
                    .metadata(metadata)
                    .externalReference("venta-" + venta.getId())
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            logger.info("Preferencia creada con ID: {}", preference.getId());
            logger.info("init_point: {}", preference.getInitPoint());

            return ResponseEntity.ok(preference.getInitPoint());

        } catch (MPException | MPApiException e) {
            logger.error("Error al crear la preferencia: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error interno del servidor.");
        }
    }


    @PostMapping("/webhooks")
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {
        try {
            logger.info("=== [handleWebhook] Webhook recibido ===: {}", payload);

            String eventType = (String) payload.get("type");
            if ("payment".equals(eventType)) {
                Map<String, Object> data = (Map<String, Object>) payload.get("data");
                if (data == null || !data.containsKey("id")) {
                    logger.warn("No se recibió 'id' en el payload para un evento de pago.");
                    return ResponseEntity.badRequest().body("Payload inválido: falta 'id'.");
                }

                String paymentIdStr = data.get("id").toString();
                logger.info("Procesando pago con ID: {}", paymentIdStr);

                Long paymentId = Long.valueOf(paymentIdStr);
                PaymentClient paymentClient = new PaymentClient();
                Payment payment = paymentClient.get(paymentId);

                if (payment == null) {
                    logger.warn("No se encontró el pago con ID: {}", paymentIdStr);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("No se encontró el pago en Mercado Pago.");
                }

                String estado = payment.getStatus();
                String externalReference = payment.getExternalReference();
                logger.info("Estado del pago en Mercado Pago: {}, External Reference: {}", estado, externalReference);

                Map<String, Object> metadata = payment.getMetadata();
                logger.info("Metadata retornada por Mercado Pago: {}", metadata);

                if (metadata == null || metadata.isEmpty()) {
                    logger.warn("El pago aprobado no tiene metadata. Se ignora el webhook.");
                    return ResponseEntity.ok("Webhook ignorado: falta metadata.");
                }

                if (payment.getPayer() == null || payment.getPayer().getEmail() == null) {
                    logger.warn("No se encontró email del pagador. Se ignora el webhook.");
                    return ResponseEntity.ok("Webhook ignorado: falta email del pagador.");
                }

                if (payment.getTransactionAmount() == null || payment.getTransactionAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    logger.warn("El pago tiene un monto inválido (cero o negativo). Se ignora el webhook.");
                    return ResponseEntity.ok("Webhook ignorado: monto inválido.");
                }

                // =====================
                // 1) Buscamos la venta
                // =====================
                Venta ventaEntity = null;
                if (externalReference != null && externalReference.startsWith("venta-")) {
                    String ventaIdStr = externalReference.split("-")[1];
                    Long ventaId = Long.valueOf(ventaIdStr);

                    ventaEntity = ventaRepository.findById(ventaId).orElse(null);
                    if (ventaEntity != null) {
                        // (Aún no actualizamos la venta; primero veremos si está repetido)
                        logger.info("Venta {} encontrada en la BD con estado: {}", ventaId, ventaEntity.getEstado());
                    }
                }

                // ============================
                // 2) Solo si estado=approved
                // ============================
                if ("approved".equalsIgnoreCase(estado)) {
                    // Chequeamos plan, plantilla, etc., como ya haces:
                    String plan = (String) metadata.get("plan");
                    String plantilla = (String) metadata.get("plantilla");
                    String nombre = (String) metadata.get("nombre");
                    String apellido = (String) metadata.get("apellido");
                    String emailCliente = (String) metadata.get("email");
                    String telefono = (String) metadata.get("telefono");
                    String novios = (String) metadata.get("novios");
                    String fechaHora = (String) metadata.get("fecha_hora");
                    String datosBancarios = (String) metadata.get("datos_bancarios");
                    String dressCode = (String) metadata.get("dress_code");
                    String mensajePersonalizado = (String) metadata.get("mensaje");
                    String cancion = (String) metadata.get("cancion");
                    String instagramWall = (String) metadata.get("instagram_wall");
                    String linkEvento = (String) metadata.get("link_evento");
                    String linkCeremonia = (String) metadata.get("link_ceremonia");
                    String comentariosAdicionales = (String) metadata.get("comentarios_adicionales");

                    if (plan == null || plantilla == null || nombre == null || emailCliente == null) {
                        logger.warn("Faltan datos esenciales en el pago aprobado. Se ignora el webhook.");
                        return ResponseEntity.ok("Webhook ignorado: falta información esencial.");
                    }

                    // ===================================
                    // 3) Verificamos si ya estaba APPROVED
                    // ===================================
                    if (ventaEntity != null) {
                        if ("APPROVED".equalsIgnoreCase(ventaEntity.getEstado())) {
                            logger.info("La venta ya estaba en estado APPROVED. Se ignora reintento. (PaymentID: {})", paymentIdStr);
                            return ResponseEntity.ok("Ya estaba en estado APPROVED. Ignorando reintento.");
                        } else {
                            // Si no estaba APPROVED, la actualizamos
                            ventaEntity.setEstado("APPROVED");
                            ventaRepository.save(ventaEntity);
                            logger.info("Estado de la venta {} actualizado a: APPROVED", ventaEntity.getId());
                        }
                    }

                    // ======================
                    // 4) Enviar los correos
                    // ======================
                    String asuntoPropietario = "Pago confirmado en Invitarly (ID " + paymentIdStr + ")";
                    String mensajePropietario = String.format(
                            "¡Se ha confirmado un pago!\n\n"
                                    + "ID de Pago: %s\n"
                                    + "Plan: %s\n"
                                    + "Plantilla: %s\n"
                                    + "---------------------------------\n"
                                    + "Datos del Comprador:\n"
                                    + "Nombre: %s %s\n"
                                    + "Email: %s\n"
                                    + "Teléfono: %s\n"
                                    + "---------------------------------\n"
                                    + "Datos de la Invitación:\n"
                                    + "Novios: %s\n"
                                    + "Fecha y Hora: %s\n"
                                    + "Link Evento: %s\n"
                                    + "Link Ceremonia: %s\n"
                                    + "Datos Bancarios: %s\n"
                                    + "Dress Code: %s\n"
                                    + "Mensaje: %s\n"
                                    + "Canción: %s\n"
                                    + "Instagram Wall: %s\n"
                                    + "Comentarios Adicionales: %s\n\n"
                                    + "¡Revisa más detalles en tu panel de Mercado Pago!",
                            paymentIdStr, plan, plantilla,
                            nombre, apellido, emailCliente, telefono,
                            novios, fechaHora, linkEvento, linkCeremonia,
                            datosBancarios, dressCode, mensajePersonalizado,
                            cancion, instagramWall, comentariosAdicionales
                    );

                    emailService.enviarCorreo(correoPropietario, asuntoPropietario, mensajePropietario);

                    String asuntoCliente = "¡Tu pago ha sido confirmado!";
                    String mensajeCliente = String.format(
                            "Hola %s %s,\n\n"
                                    + "¡Gracias por tu compra en Invitarly!\n"
                                    + "Ya estamos preparando tu invitación para la boda de %s.\n\n"
                                    + "En breve nos pondremos en contacto contigo para confirmar cualquier detalle adicional.\n"
                                    + "Si necesitas algo más, no dudes en escribirnos.\n\n"
                                    + "¡Felicitaciones y gracias por confiar en nosotros!",
                            (nombre != null ? nombre : ""),
                            (apellido != null ? apellido : ""),
                            (novios != null ? novios : "los novios")
                    );

                    emailService.enviarCorreo(emailCliente, asuntoCliente, mensajeCliente);
                    logger.info("Correos enviados exitosamente para el pago ID: {}", paymentIdStr);

                } else {
                    logger.info("El pago con ID {} no está aprobado. Estado actual: {}", paymentIdStr, estado);

                    // Si deseas, puedes actualizar la venta a PENDING, IN_PROCESS, etc.
                    // O dejarlo como está.
                    if (externalReference != null && externalReference.startsWith("venta-")) {
                        String ventaIdStr = externalReference.split("-")[1];
                        Long ventaId = Long.valueOf(ventaIdStr);
                        ventaRepository.findById(ventaId).ifPresent(venta -> {
                            venta.setEstado(estado.toUpperCase());
                            ventaRepository.save(venta);
                            logger.info("Estado de la venta {} actualizado a: {}", ventaId, estado);
                        });
                    }
                }

            } else {
                logger.info("El evento recibido no es de tipo 'payment'. Se recibió: {}", eventType);
            }

            return ResponseEntity.ok("Webhook procesado correctamente");
        } catch (MPException | MPApiException e) {
            logger.error("Error consultando Mercado Pago: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error consultando detalles del pago en Mercado Pago");
        } catch (Exception e) {
            logger.error("Error procesando el webhook: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor");
        }
    }
}