package com.invitarly.invitarlyweb.repository;

import com.invitarly.invitarlyweb.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Optional<Plan> findByNombre(String nombre);
}
