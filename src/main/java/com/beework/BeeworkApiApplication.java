package com.beework;

import com.beework.models.Commento;
import com.beework.models.Progetto;
import com.beework.models.Task;
import com.beework.models.Utente;
import com.beework.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class BeeworkApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeworkApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UtenteRepository utenteRepository, TaskRepository taskRepository, ProgettiRepository progettiRepository, NotificaRepository notificaRepository, CommentoRepository commentoRepository) {
        return (args) -> {
            Progetto p1 = new Progetto("Paleontologia", "Corso di Paleontologia", 0);
            Progetto p2 = new Progetto("Etologia", "Corso di Etologia", 20);
            Progetto p3 = new Progetto("Archeologia", "Corso di Archeologia", 50);
            progettiRepository.save(p1);
            progettiRepository.save(p2);
            progettiRepository.save(p3);

            Utente u1 = new Utente("Ciccio97", "fkjakmk3", "Francesco", "Pancetta", "ciccio.pancetta@gmail.com", "");
            Utente u2 = new Utente("RCarlos3", "32uhd92", "Roberto", "Carlos", "carlos3@gmail.com", "Campione del Mondo 2002");
            Utente u3 = new Utente("cristiano", "423d32ss", "Cristiano", "Ronaldo", "cr7@outlook.com", "GOAT");
            utenteRepository.save(u1);
            utenteRepository.save(u2);
            utenteRepository.save(u3);

            Task t1 = new Task("Ricerca sul T-Rex", LocalDate.of(2021,7,31), "bassa", false, "Approfondire la relazione con il triceratopo", "Università", p1);
            taskRepository.save(t1);
            Task t2 = new Task("Scavi a Pompei", LocalDate.of(2022,3,15), "bassa", false, "Restauro utensili", "Università", p3);
            taskRepository.save(t2);
            Task t3 = new Task("Studio tapiro venezuelano", LocalDate.of(2021,9,30), "alta", false, "Approfondire il comportamento del tapiro in inverno", "Università", p2);
            taskRepository.save(t3);

            Commento c1 = new Commento("Andiamo a vederlo allo zoo!", t3, u1);
            commentoRepository.save(c1);
            Commento c2 = new Commento("Non sto nella pelle!", t2, u2);
            commentoRepository.save(c2);
            Commento c3 = new Commento("Preferivo il velociraptor", t1, u3);
            commentoRepository.save(c3);
            Commento c4 = new Commento("Davvero!", t2, u1);
            commentoRepository.save(c4);
        };
    }
}
