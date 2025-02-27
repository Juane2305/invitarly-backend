package com.invitarly.invitarlyweb.service;

import com.invitarly.invitarlyweb.model.Plan;
import com.invitarly.invitarlyweb.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public List<Plan> obtenerPlanes() {
        return planRepository.findAll();
    }

    public Plan obtenerPlanPorId(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));
    }

    public Plan guardarPlan(Plan plan) {
        return planRepository.save(plan);
    }

    public Plan obtenerPlanPorNombre(String nombre) {
        return planRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Plan no encontrado"));
    }

    public List<String> obtenerFuncionalidadesPorNombre(String nombre) {
        Plan plan = obtenerPlanPorNombre(nombre);
        return plan.getFuncionalidades();
    }
}