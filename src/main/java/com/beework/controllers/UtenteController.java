package com.beework.controllers;

import com.beework.models.Progetto;
import com.beework.models.Utente;
import com.beework.repositories.UtenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("utenti")
public class UtenteController {
    private final Logger logger = LoggerFactory.getLogger(UtenteController.class);
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping("/tutti")
    public ResponseEntity<List<Utente>> getAllUser(Principal principal) {
        Optional<Utente> utente = this.utenteRepository.findByEmail(principal.getName());
        List<Utente> utenti = this.utenteRepository.findAll().stream()
                    .filter(u -> !u.equals(utente.get()))
                    .collect(Collectors.toList());
        return ResponseEntity.ok(utenti);
    }


    @PostMapping
    public ResponseEntity<Utente> addUtente(@RequestBody Utente utente){
        return ResponseEntity.status(201).body(this.utenteRepository.save(utente));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long userId) {
        Optional<Utente> utente = this.utenteRepository.findById(userId);

        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utente.get());
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
    public ResponseEntity<Utente> updateUtente(@RequestBody Utente utente, Principal principal) {
        Optional<Utente> u = this.utenteRepository.findByEmail(principal.getName());
        if (u.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        logger.info("{}",utente);
        u.get().setBio(utente.getBio());
        utenteRepository.saveAndFlush(u.get());

        return ResponseEntity.status(200).body(u.get());
    }
}
