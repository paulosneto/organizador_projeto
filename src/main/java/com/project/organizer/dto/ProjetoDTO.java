package com.project.organizer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Timestamp;

public record ProjetoDTO(@NotNull @JsonProperty String nomeProjeto,
                         @JsonProperty String descricaoProjeto,
                         @NotNull @JsonProperty String statusProjeto,
                         @JsonProperty double orcamentoProjeto) {
}
