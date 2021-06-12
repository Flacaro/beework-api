package com.beework.repositories;

import com.beework.models.Notifica;
import com.beework.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificaRepository extends JpaRepository<Notifica,Long> {
    List<Notifica> findNotificaByUtente(Utente utente);
}
