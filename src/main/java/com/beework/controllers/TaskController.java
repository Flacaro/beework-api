package com.beework.controllers;

import com.beework.models.*;
import com.beework.repositories.NotificaRepository;
import com.beework.repositories.TaskRepository;
import com.beework.repositories.UtenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("tasks")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private NotificaRepository notificaRepository;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        this.logger.info("ciao");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask( @PathVariable Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task.get());
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.status(201).body(taskRepository.save(task));
    }

    @PostMapping("/{taskId}/members")
    public ResponseEntity<?> aggiungiMembri(@RequestBody EntityId entityId, @PathVariable Long taskId) {
        Optional<Task> task = this.taskRepository.findById(taskId);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Optional<Utente> utente = this.utenteRepository.findById(entityId.getEntityId());
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        task.get().getMembri().add(utente.get());
        Notifica notifica = new Notifica("Nuovo Task", "Sei stato aggiunto al task " + task.get().getNome(), utente.get());
        this.notificaRepository.save(notifica);
        this.taskRepository.flush();
        return ResponseEntity.status(201).body(task);
    }
}

