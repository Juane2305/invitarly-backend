package com.invitarly.invitarlyweb.controller;

import com.invitarly.invitarlyweb.model.Plan;
import com.invitarly.invitarlyweb.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
@CrossOrigin(origins = "http://localhost:5173")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping
    public List<Plan> obtenerPlanes() {
        return planService.obtenerPlanes();
    }

    @GetMapping("/{id}")
    public Plan obtenerPlan(@PathVariable Long id) {
        return planService.obtenerPlanPorId(id);
    }

    @PostMapping
    public Plan crearPlan(@RequestBody Plan plan) {
        return planService.guardarPlan(plan);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Plan> obtenerPlanPorNombre(@PathVariable String nombre) {
        Plan plan = planService.obtenerPlanPorNombre(nombre);
        if (plan != null) {
            return ResponseEntity.ok(plan);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{nombre}/funcionalidades")
    public ResponseEntity<List<String>> obtenerFuncionalidadesPorPlan(@PathVariable String nombre) {
        Plan plan = planService.obtenerPlanPorNombre(nombre);
        if (plan != null) {
            return ResponseEntity.ok(plan.getFuncionalidades());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}