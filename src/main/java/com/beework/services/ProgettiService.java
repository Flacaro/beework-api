package com.beework.services;


import com.beework.models.Progetto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Business service
@Service
public class ProgettiService {
    List<Progetto> progetti = new ArrayList<> (Arrays.asList(
            new Progetto("1","Studio delfini", "Devono essere aiutati"),
            new Progetto("2","Ricerca sul citoplasma","cellule")
    ));

    public List<Progetto> getProgetti() {
    return progetti;
}

    public Progetto getProgetto(String id){
        return progetti.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    public void aggiungiProgetto(Progetto progetto){
    progetti.add(progetto);
}

    public void eliminaProgetto(String id){
    progetti.stream().filter(t -> t.getId().equals(id));
}

    public void modificaProgetto(String id, Progetto progetto) {
    //loop per trovare quel progetto specifico
        for(int i = 0; i < progetti.size(); i++){
            Progetto t = progetti.get(i);
            if (t.getId().equals(id)) {
                progetti.set(i, progetto);
                return;
            }
        }
    }
}