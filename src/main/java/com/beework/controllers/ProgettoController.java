package com.beework.controllers;

import com.beework.models.EntityId;
import com.beework.models.Progetto;
import com.beework.models.Utente;
import com.beework.repositories.ProgettiRepository;
import com.beework.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("progetti")
public class ProgettoController {
    @Autowired
    private ProgettiRepository progettiRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping("/{progettoId}")
    public ResponseEntity<?> getProgetto(@PathVariable Long progettoId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(201).body(progetto.get());
    }

    @PostMapping
    public ResponseEntity<Progetto> aggiungiProgetto(@RequestBody Progetto progetto) {
        return ResponseEntity.status(201).body(this.progettiRepository.save(progetto));
    }

    @DeleteMapping("/{progettoId}")
    public ResponseEntity<?> eliminaProgetto(@PathVariable Long progettoId) {
        if (!this.progettiRepository.existsById(progettoId)) {
            return ResponseEntity.notFound().build();
        }

        this.progettiRepository.deleteById(progettoId);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/{progettoId}/utenti")
    public ResponseEntity<?> aggiungiMembro(@RequestBody EntityId entityId, @PathVariable Long progettoId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Utente> utente = this.utenteRepository.findById(entityId.getEntityId());
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        progetto.get().getMembriProgetto().add(utente.get());
        this.progettiRepository.flush();
        return ResponseEntity.status(201).body(progetto);
    }

    @GetMapping("/{progettoId}/utenti")
    public ResponseEntity<?> getMembri(@PathVariable Long progettoId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(201).body(progetto.get().getMembriProgetto());
    }

    @DeleteMapping("/{progettoId}/utenti/{utenteId}")
    public ResponseEntity<?> eliminaMembro(@PathVariable Long progettoId, @PathVariable Long utenteId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Utente> utenti = progetto.get().getMembriProgetto();
        for (Utente u : utenti) {
            if (u.getId().equals(utenteId)) {
                progetto.get().getMembriProgetto().remove(u);
                this.progettiRepository.flush();
                return ResponseEntity.status(200).body(progetto);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
