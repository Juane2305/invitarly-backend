package com.invitarly.invitarlyweb.repository;

import com.invitarly.invitarlyweb.model.Plantilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantillaRepository extends JpaRepository<Plantilla, Long> {

    Plantilla findByNombre(String nombre);

}