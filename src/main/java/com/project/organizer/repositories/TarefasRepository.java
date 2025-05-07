package com.project.organizer.repositories;

import com.project.organizer.models.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefasRepository extends JpaRepository<Tarefas, Integer> {
}
