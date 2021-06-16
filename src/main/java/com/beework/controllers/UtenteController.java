package com.beework.controllers;

import com.beework.models.Progetto;
import com.beework.models.Utente;
import com.beework.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("utenti")
public class UtenteController {
    @Autowired
    private UtenteRepository utenteRepository;

    @PostMapping
    public ResponseEntity<Utente> addUtente(@RequestBody Utente utente){
        return ResponseEntity.status(201).body(this.utenteRepository.save(utente));
    }

    @GetMapping
    public ResponseEntity<?> getUtente(Principal principal) {
        Optional<Utente> utente = this.utenteRepository.findByEmail(principal.getName());
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(utente.get());
    }

    @GetMapping("/{utenteId}/progetti")
    public ResponseEntity<?> getProgetti(@PathVariable Long utenteId) {
        Optional<Utente> utente = this.utenteRepository.findById(utenteId);
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(utente.get().getListaProgetti());
    }

    @PutMapping
    public ResponseEntity<Utente> updateUtente(Principal principal) {
        Optional<Utente> u = this.utenteRepository.findByEmail(principal.getName());
        if (u.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(u.get());
    }
}
