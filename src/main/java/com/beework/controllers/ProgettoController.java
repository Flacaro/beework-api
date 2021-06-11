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
@RequestMapping("progetti")
public class ProgettoController {
    @Autowired
    private ProgettiRepository progettiRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentoRepository commentoRepository;

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
        progetto.get().getMembri().add(utente.get());
        this.progettiRepository.flush();
        return ResponseEntity.status(201).body(progetto);
    }

    @GetMapping("/{progettoId}/utenti")
    public ResponseEntity<?> getMembri(@PathVariable Long progettoId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(201).body(progetto.get().getMembri());
    }

    @DeleteMapping("/{progettoId}/utenti/{utenteId}")
    public ResponseEntity<?> eliminaMembro(@PathVariable Long progettoId, @PathVariable Long utenteId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Utente> utenti = progetto.get().getMembri();
        for (Utente u : utenti) {
            if (u.getId().equals(utenteId)) {
                progetto.get().getMembri().remove(u);
                this.progettiRepository.flush();
                return ResponseEntity.status(200).body(progetto);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{progettoId}/tasks")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable("progettoId") Long progettoId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);

        if(progetto.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(progetto.get().getTasks());
    }


    @PostMapping("/{progettoId}/tasks")
    public ResponseEntity<Task> salvaTask(@PathVariable("progettoId") Long progettoId, @RequestBody Task task) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);

        if(progetto.isEmpty()) return ResponseEntity.notFound().build();

        Task taskSalvato = this.taskRepository.save(task);

        progetto.get().getTasks().add(taskSalvato);
        taskSalvato.setProgetto(progetto.get());
        this.progettiRepository.flush();

        return ResponseEntity.status(201).body(taskSalvato);
    }

    // progetto X e Z
    // task del progetto X hanno id 1 e 2
    // task del progetto Z hanno id 3 e 4
    // /progetti/X/tasks/3/commenti

    @GetMapping("/{progettoId}/tasks/{taskId}/commenti")
    public ResponseEntity<List<Commento>> getAllComments(
            @PathVariable("progettoId") Long progettoId,
            @PathVariable("taskId") Long taskId) {

        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        Optional<Task> task = this.taskRepository.findById(taskId);

        if(progetto.isEmpty() || task.isEmpty() ) return ResponseEntity.notFound().build();

        // controlla se il task è nel progetto
        if(!progetto.get().getTasks().contains(task.get())) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(task.get().getCommenti());
    }

    @PostMapping("/{progettoId}/tasks/{taskId}/commenti")
    public ResponseEntity<Commento> salvaCommento(
            @PathVariable("progettoId") Long progettoId,
            @PathVariable("taskId") Long taskId,
            @RequestBody Commento commento) {

        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        Optional<Task> task = this.taskRepository.findById(taskId);

        if(progetto.isEmpty() || task.isEmpty() ) return ResponseEntity.notFound().build();

        // controlla se il task è nel progetto
        if(!progetto.get().getTasks().contains(task.get())) return ResponseEntity.notFound().build();

        Commento commentoSalvato = this.commentoRepository.save(commento);

        task.get().getCommenti().add(commentoSalvato);
        this.taskRepository.flush();

        return ResponseEntity.status(201).body(commentoSalvato);
    }

}
