package com.project.organizer.repositories;

import com.project.organizer.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
List<Projeto> findProjetoByNomeProjetoContainingIgnoreCaseOrDescricaoProjetoContainingIgnoreCase(String nome, String descricao);
}
