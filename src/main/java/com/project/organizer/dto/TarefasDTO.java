package com.project.organizer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TarefasDTO(@NotBlank(message = "Descição é obrigatória") String descricaoTarefa,
                          Integer idprojeto,
                          LocalDate dataInicio,
                          LocalDate dataFim,
                          Integer idTarefaPredecedora,
                          String statusTarefa) {
}
