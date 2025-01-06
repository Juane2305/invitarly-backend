package com.invitarly.invitarlyweb.repository;

import com.invitarly.invitarlyweb.model.Plantilla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlantillaRepository extends JpaRepository<Plantilla, Long>{
}
