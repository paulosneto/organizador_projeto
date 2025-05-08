package com.project.organizer.repositories;

import com.project.organizer.models.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefasRepository extends JpaRepository<Tarefas, Integer> {
    List<Tarefas> findTarefaByDescricaoTarefaContainingIgnoreCase(String valor);
}
