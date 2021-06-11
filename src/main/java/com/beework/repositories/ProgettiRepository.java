package com.beework.repositories;

import com.beework.models.Progetto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgettiRepository extends JpaRepository<Progetto,Long> {
}
