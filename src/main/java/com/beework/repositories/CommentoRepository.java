package com.beework.repositories;

import com.beework.models.Commento;
import com.beework.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentoRepository extends JpaRepository<Commento,Long> {
    List<Commento> findCommentoByTask(Task task);
}
