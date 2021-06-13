package com.beework.auth;

import com.beework.models.Utente;
import com.beework.repositories.UtenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DettagliUtenteService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(DettagliUtenteService.class);

    private final UtenteRepository utenteRepository;

    public DettagliUtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }
    @Override
    public DettagliUtente loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Utente> utente = utenteRepository.findByEmail(email);

        if(utente.isEmpty()) throw new UsernameNotFoundException("Could not find any user with provided credentials");

        return new DettagliUtente(utente.get().getEmail(), utente.get().getPassword());
    }

}
