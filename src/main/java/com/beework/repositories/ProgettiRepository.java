package com.beework.repositories;

import com.beework.models.Progetto;
import com.beework.models.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgettiRepository extends JpaRepository<Progetto,Long> {
    List<Progetto> findProgettoByMembriProgettoContains(Utente utente);
}
