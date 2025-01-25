package com.invitarly.invitarlyweb.service;

import org.springframework.stereotype.Service;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    public String crearPreferencia(String planNombre) throws MPException {
        MercadoPago.SDK.setAccessToken(accessToken);

        Preference preference = new Preference();
        Item item = new Item();
        item.setTitle(planNombre)
                .setQuantity(1)
                .setUnitPrice((float) 100.0); // Cambia esto dinámicamente según el plan.
        preference.appendItem(item);

        // URL de retorno
        preference.setBackUrls(new BackUrls()
                .setSuccess("http://localhost:5173/success")
                .setFailure("http://localhost:5173/failure")
                .setPending("http://localhost:5173/pending"));

        preference.setAutoReturn(Preference.AutoReturn.approved);

        return preference.save().getSandboxInitPoint(); // En producción, usa getInitPoint().
    }
}
