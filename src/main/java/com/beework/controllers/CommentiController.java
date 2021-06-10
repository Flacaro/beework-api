package com.beework.controllers;

import com.beework.models.Commento;
import com.beework.models.Progetto;
import com.beework.services.CommentiService;
import com.beework.services.ProgettiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentiController {
    @Autowired
    private CommentiService commentiService;

    @RequestMapping("progetti/{progettoId}/tasks/{taskId}/commenti")
    public List<Commento> getCommenti(){
        return commentiService.getCommenti();
    }

    @RequestMapping(method = RequestMethod.POST, value = "progetti/{progettoId}/tasks/{taskId}/commenti")
    public void aggiungiCommento(@RequestBody Commento commento) {
        commentiService.aggiungiCommento(commento);
    }

}
