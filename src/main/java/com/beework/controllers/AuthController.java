package com.beework.controllers;

import com.beework.auth.DettagliUtente;
import com.beework.auth.DettagliUtenteService;
import com.beework.models.Token;
import com.beework.models.Utente;
import com.beework.models.auth.Login;
import com.beework.models.auth.Registrazione;
import com.beework.repositories.UtenteRepository;
import com.beework.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UtenteRepository utenteRepository;

    private final DettagliUtenteService dettagliUtenteService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public AuthController(UtenteRepository utenteRepository,
                        DettagliUtenteService dettagliUtenteService,
                        AuthenticationManager authenticationManager,
                        JwtUtil jwtUtil) {
        this.utenteRepository = utenteRepository;
        this.dettagliUtenteService = dettagliUtenteService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<Token> loginAndCreateToken(@Valid @RequestBody Login authenticationRequest) {
        this.logger.info("Login request {}", authenticationRequest);
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch(BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        }

        final DettagliUtente userDetails = dettagliUtenteService.loadUserByUsername(authenticationRequest.getEmail());
        String jwt = this.jwtUtil.generateToken(userDetails);

        Token token = new Token(jwt, this.jwtUtil.extractExpiration(jwt));

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }


    @PostMapping("/registrazione")
    public ResponseEntity<Utente> register(@Valid @RequestBody Utente utente) {
        utente.setPassword(new BCryptPasswordEncoder().encode(utente.getPassword()));
        this.utenteRepository.save(utente);
        return ResponseEntity.status(201).build();
    }


}
