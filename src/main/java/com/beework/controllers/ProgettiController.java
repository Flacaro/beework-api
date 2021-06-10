package com.beework.controllers;

import com.beework.models.Progetto;
import com.beework.services.ProgettiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProgettiController {

    //devo richiedere un'istanza di ProfiloService
    //in questo modo siamo sicuri che per i metodi
    // definiti in Profilo c'è un'istanza del ProfileService disponibile
    @Autowired
    private ProgettiService progettiService;

    @RequestMapping("progetti")
    //viene convertito in json automaticamente
    public List<Progetto> getProgetti(){
        return progettiService.getProgetti();
    }

    @RequestMapping("progetti/{id}")
    public Progetto getProgetto(@PathVariable String id){
        return progettiService.getProgetto(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "progetti")
    public void aggiungiProgetto(@RequestBody Progetto progetto) {
        //ho bisogno di avere il body nel payload della richiesta http
        //uso @RequestBody
        progettiService.aggiungiProgetto(progetto);
        //usare postman
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "progetti/{id}")
    public void eliminaProgetto(@PathVariable String id){
        progettiService.eliminaProgetto(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "progetti/{id}")
    public void modificaProgetto(@RequestBody Progetto progetto, @PathVariable String id){
        progettiService.modificaProgetto(id , progetto);
    }


}
