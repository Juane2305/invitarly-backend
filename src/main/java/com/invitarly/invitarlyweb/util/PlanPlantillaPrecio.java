package com.invitarly.invitarlyweb.util;

import java.util.HashMap;
import java.util.Map;

public class PlanPlantillaPrecio {

    private static final Map<String, Double> precios = new HashMap<>();

    static {
        precios.put("basico-vintage", 28000.0);
        precios.put("basico-moderna", 28000.0);
        precios.put("basico-elegante", 28000.0);

        precios.put("silver-vintage", 35000.0);
        precios.put("silver-moderna", 35000.0);
        precios.put("silver-elegante", 35000.0);

        precios.put("gold-vintage", 40000.0);
        precios.put("gold-moderna", 40000.0);
        precios.put("gold-elegante", 40000.0);
    }

    public static Double getPrecio(String plan, String plantilla) {
        return precios.getOrDefault(plan + "-" + plantilla, 0.0);
    }
}
