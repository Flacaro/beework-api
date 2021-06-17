package com.beework.controllers;

import com.beework.models.Notifica;
import com.beework.models.Utente;
import com.beework.repositories.NotificaRepository;
import com.beework.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("notifiche")
public class NotificaController {
    @Autowired
    private NotificaRepository notificaRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public ResponseEntity<List<Notifica>> getNotifiche(Principal principal) {
        Optional<Utente> utente = this.utenteRepository.findByEmail(principal.getName());
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notificaRepository.findNotificaByUtente(utente.get()));
    }
}
