package com.beework.controllers;

import com.beework.models.Utente;
import com.beework.services.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtentiController {
    @Autowired
    private UtentiService utentiService;

    @RequestMapping("utenti")
    public List<Utente> getUtenti(){
        return utentiService.getUtenti();
    }

    @RequestMapping("utenti/{username}")
    public Utente getUtente(@PathVariable String username){
        return utentiService.getUtente(username);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "utenti/{username}")
    public void eliminaProgetto(@PathVariable String username){
        utentiService.eliminaUtente(username);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "utenti/{username}")
    public void modificaProgetto(@RequestBody Utente utente, @PathVariable String username){
        utentiService.modificaUtente(username , utente);
    }
}
