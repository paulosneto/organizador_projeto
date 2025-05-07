package com.project.organizer.dto;

import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Timestamp;

public record ProjetoDTO(@NotNull String nomeProjeto, @NotNull String descricaoProjeto, @NotNull String statusProjeto, double orcamentoProjeto, Timestamp dataInicio, Timestamp dataFim) {
}
