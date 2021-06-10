package com.beework.controllers;

import com.beework.models.Utente;
import com.beework.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("utenti")
public class UtenteController {
    @Autowired
    private UtenteRepository utenteRepository;

    @PostMapping
    public ResponseEntity<Utente> addUtente(@RequestBody Utente utente){
        return ResponseEntity.status(201).body(this.utenteRepository.save(utente));
    }
}
