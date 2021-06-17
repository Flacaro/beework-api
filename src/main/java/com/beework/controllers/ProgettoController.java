package com.beework.controllers;

import com.beework.models.*;
import com.beework.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.security.Principal;
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
    private NotificaRepository notificaRepository;

    private final Logger logger = LoggerFactory.getLogger(ProgettoController.class);

    @GetMapping
    public ResponseEntity<List<Progetto>> getProgettiUtente(Principal principal) {
        Optional<Utente> utente = this.utenteRepository.findByEmail(principal.getName());
        if (utente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(utente.get().getListaProgetti());
    }

    @GetMapping("/{progettoId}")
    public ResponseEntity<?> getProgetto(@PathVariable Long progettoId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(201).body(progetto.get());
    }

    /*
    @GetMapping()
    public ResponseEntity<?> getProgettoByUtente(@RequestBody Utente utente) {
        Optional<Utente> u1 = this.utenteRepository.findById(utente.getId());
        if (u1.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(progettiRepository.findProgettoByMembriProgettoContains(u1.get()));
    }
*/

    @PostMapping
    public ResponseEntity<Progetto> aggiungiProgetto(@RequestBody Progetto progetto, Principal principal) {
        Utente utente = this.utenteRepository.findByEmail(principal.getName())
                .orElseThrow(EntityExistsException::new);
        progetto.getMembriProgetto().add(utente);
        this.progettiRepository.flush();
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
        Notifica notifica = new Notifica("Nuovo Progetto", "Sei stato aggiunto al progetto " + progetto.get().getNome(), utente.get());
        this.notificaRepository.save(notifica);
        this.progettiRepository.flush();
        return ResponseEntity.status(201).body(progetto);
    }

    @GetMapping("/{progettoId}/utenti")
    public ResponseEntity<?> getMembri(@PathVariable Long progettoId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);
        if (progetto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(200).body(progetto.get().getMembriProgetto());
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


    @GetMapping("/{progettoId}/tasks")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable("progettoId") Long progettoId) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);

        if(progetto.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(progetto.get().getTasks());
    }

    @PostMapping("/{progettoId}/tasks")
    public ResponseEntity<Task> aggiungiTask(@PathVariable("progettoId") Long progettoId, @RequestBody Task task) {
        Optional<Progetto> progetto = this.progettiRepository.findById(progettoId);

        if(progetto.isEmpty()) return ResponseEntity.notFound().build();

        Task taskSalvato = this.taskRepository.save(task);

        progetto.get().getTasks().add(taskSalvato);
        taskSalvato.setProgetto(progetto.get());
        this.progettiRepository.flush();

        return ResponseEntity.status(201).body(taskSalvato);
    }
}
