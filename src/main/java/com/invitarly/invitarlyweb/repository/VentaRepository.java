package com.invitarly.invitarlyweb.repository;

import com.invitarly.invitarlyweb.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}