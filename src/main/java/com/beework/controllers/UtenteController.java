package com.beework.controllers;

import com.beework.models.Progetto;
import com.beework.models.Utente;
import com.beework.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{utenteId}")
    public ResponseEntity<?> getUtente(@PathVariable Long utenteId) {
        Optional<Utente> utente = this.utenteRepository.findById(utenteId);
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(201).body(utente.get());
    }
}
