package com.beework.services;

import com.beework.models.Utente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UtentiService {
    List<Utente> utenti = new ArrayList<>(Arrays.asList(
            new Utente("gucciBoy", "d328h923", "Bello", "Figo", "gucciBoy@gmail.com", "Sciao beli"),
            new Utente("freigeist", "dionisiaco34783", "Friedrich", "Nietzsche", "zarathustra@live.com", "Vi annuncio il superuomo"),
            new Utente("arthur88", "lavitafaschifo11", "Arthur", "Schopenhauer", "velodimaya@live.com", "Hegel colera sei la vergogna della filosofia intera")
    ));

    public List<Utente> getUtenti() {
        return utenti;
    }

    public Utente getUtente(String username){
        return utenti.stream().filter(t -> t.getUsername().equals(username)).findFirst().get();
    }

    /* DA FARE CON IL SERVIZIO DI AUTENTICAZIONE
    public void aggiungiUtente(Utente progetto){
        utenti.add(progetto);
    }
     */

    public void eliminaUtente(String username){
        utenti.stream().filter(t -> t.getUsername().equals(username));
    }

    public void modificaUtente(String username, Utente utente) {
        for (int i = 0; i < utenti.size(); i++){
            Utente t = utenti.get(i);
            if (t.getUsername().equals(username)) {
                utenti.set(i, utente);
                return;
            }
        }
    }
}
