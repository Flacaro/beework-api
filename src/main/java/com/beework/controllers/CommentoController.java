package com.beework.controllers;

import com.beework.models.*;
import com.beework.repositories.CommentoRepository;
import com.beework.repositories.ProgettiRepository;
import com.beework.repositories.TaskRepository;
import com.beework.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("progetti/{progettoId}/tasks/{taskId}/commenti")
public class CommentoController {
    @Autowired
    private CommentoRepository commentoRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProgettiRepository progettiRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public ResponseEntity<List<Commento>> getCommenti(@PathVariable Long progettoId, @PathVariable Long taskId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Task> task = this.taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentoRepository.findCommentoByTask(task.get()));
    }

    @PostMapping("/utente/{utenteId}")
    public ResponseEntity<Commento> aggiungiCommento(@PathVariable Long progettoId, @PathVariable Long taskId,@PathVariable Long utenteId, @RequestBody Commento commento) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Task> task = this.taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Utente> utente = this.utenteRepository.findById(utenteId);
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        commento.setTask(task.get());
        commento.setUtente(utente.get());
        this.commentoRepository.save(commento);
        return ResponseEntity.status(201).body(commento);
    }
}
