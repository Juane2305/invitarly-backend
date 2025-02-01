package com.invitarly.invitarlyweb.util;

import java.util.HashMap;
import java.util.Map;

public class PlanPlantillaPrecio {

    private static final Map<String, Double> precios = new HashMap<>();

    static {
        // Configurar precios por combinación de plan y plantilla
        precios.put("basico-vintage", 25000.0);
        precios.put("basico-moderna", 25000.0);
        precios.put("basico-elegante", 25000.0);

        precios.put("silver-vintage", 28000.0);
        precios.put("silver-moderna", 28000.0);
        precios.put("silver-elegante", 28000.0);

        precios.put("gold-vintage", 35000.0);
        precios.put("gold-moderna", 10.0);
        precios.put("gold-elegante", 35000.0);
    }

    public static Double getPrecio(String plan, String plantilla) {
        return precios.getOrDefault(plan + "-" + plantilla, 0.0);
    }
}
