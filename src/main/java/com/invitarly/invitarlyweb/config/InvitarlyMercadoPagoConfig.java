package com.invitarly.invitarlyweb.config;

import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InvitarlyMercadoPagoConfig {

    @Value("${mercadopago.access-token}")
    private String accessToken;

    @PostConstruct
    public void initMercadoPago() {
        MercadoPagoConfig.setAccessToken(accessToken);
    }
}