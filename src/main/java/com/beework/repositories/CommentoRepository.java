package com.beework.repositories;

import com.beework.models.Commento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentoRepository extends JpaRepository<Commento,Long> {
}
