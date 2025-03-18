package com.invitarly.invitarlyweb.util;

import java.util.HashMap;
import java.util.Map;

public class PlanPlantillaPrecio {

    private static final Map<String, Double> precios = new HashMap<>();

    static {
        precios.put("basico-roma", 28000.0);
        precios.put("basico-berlin", 28000.0);
        precios.put("basico-tokyo", 28000.0);
        precios.put("basico-verona", 28000.0);

        precios.put("silver-roma", 35000.0);
        precios.put("silver-berlin", 35000.0);
        precios.put("silver-tokyo", 35000.0);
        precios.put("silver-verona", 35000.0);

        precios.put("gold-roma", 40000.0);
        precios.put("gold-berlin", 40000.0);
        precios.put("gold-tokyo", 45000.0);
        precios.put("gold-verona", 45000.0);
    }

    public static Double getPrecio(String plan, String plantilla) {
        return precios.getOrDefault(plan + "-" + plantilla, 0.0);
    }
}
