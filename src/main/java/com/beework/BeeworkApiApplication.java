package com.beework;

import com.beework.models.Progetto;
import com.beework.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeeworkApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeeworkApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UtenteRepository utenteRepository, TaskRepository taskRepository, ProgettiRepository progettiRepository, NotificaRepository notificaRepository, CommentoRepository commentoRepository) {
        return (args) -> {
            Progetto p1 = new Progetto();
        };
    }
}
