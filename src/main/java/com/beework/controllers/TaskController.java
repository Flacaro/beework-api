package com.beework.controllers;

import com.beework.models.Task;
import com.beework.models.EntityId;
import com.beework.models.Utente;
import com.beework.repositories.TaskRepository;
import com.beework.repositories.UtenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("tasks")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        this.logger.info("ciao");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.status(201).body(taskRepository.save(task));
    }

    @PostMapping("/{taskId}/members")
    public ResponseEntity<?> addMember(@RequestBody EntityId entityId, @PathVariable Long taskId) {
        Task task = this.taskRepository.getById(taskId);
        //dovremmo usare findById che restituisce un Optional e si può gestire
        Utente utente = utenteRepository.getById(entityId.getEntityId());
        task.getMembri().add(utente);
        utente.getListaTask().add(task);
        taskRepository.flush();
        return ResponseEntity.status(201).body(task);
    }
}

