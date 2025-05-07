package com.project.organizer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TarefasDTO(@NotNull @JsonProperty String descricaoTarefa,
                         @JsonProperty String projeto,
                         @JsonProperty LocalDateTime dataInicio,
                         @JsonProperty LocalDateTime dataFim,
                         @JsonProperty String tarefaPredecedora,
                         @JsonProperty String statusTarefa) {
}
