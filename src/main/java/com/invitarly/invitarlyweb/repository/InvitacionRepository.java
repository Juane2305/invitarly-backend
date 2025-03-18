package com.invitarly.invitarlyweb.repository;

import com.invitarly.invitarlyweb.model.Invitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitacionRepository extends JpaRepository<Invitacion, Long> {
    Invitacion findByUrlPersonalizada(String urlPersonalizada);
}