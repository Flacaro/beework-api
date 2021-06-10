package com.beework.services;

import com.beework.models.Commento;
import com.beework.models.Progetto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommentiService {
    private static CommentiService instance;

    private CommentiService() { }

    public static CommentiService getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            return new CommentiService();
        }
    }

    List<Commento> commenti = new ArrayList<>(Arrays.asList(
            new Commento("Bello Figo", "Figa zio"),
            new Commento("Ciccio Pnacetta", "Che novità ci sono?")
    ));

    public List<Commento> getCommenti() {
        return commenti;
    }

    public void aggiungiCommento(Commento commento){
        commenti.add(commento);
    }

}
