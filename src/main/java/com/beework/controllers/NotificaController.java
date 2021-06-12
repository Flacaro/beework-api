package com.beework.controllers;

import com.beework.models.Notifica;
import com.beework.models.Utente;
import com.beework.repositories.NotificaRepository;
import com.beework.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("utenti/{utenteId}/notifiche")
public class NotificaController {
    @Autowired
    private NotificaRepository notificaRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public ResponseEntity<List<Notifica>> getNotifiche(@PathVariable Long utenteId) {
        Optional<Utente> utente = this.utenteRepository.findById(utenteId);
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificaRepository.findNotificaByUtente(utente.get()));
    }
}
