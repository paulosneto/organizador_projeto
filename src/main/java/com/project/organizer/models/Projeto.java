package com.project.organizer.models;

import com.project.organizer.dto.ProjetoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "projeto")
public class Projeto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idprojeto")
    private int idProjeto;
    @NotEmpty(message = "o nome do projeto é obrigatorio")
    @Column(name = "nomeprojeto", nullable = false)
    private String nomeProjeto;
    @Column(name = "descricaoprojeto")
    private String descricaoProjeto;
    @Column(name = "statusprojeto")
    private String statusProjeto;
    @Column(name = "orcamentoprojeto")
    private double orcamentoProjeto;


    public Projeto(ProjetoDTO projetoDTO) {
        this.nomeProjeto = projetoDTO.nomeProjeto();
        this.descricaoProjeto = projetoDTO.descricaoProjeto();
        this.statusProjeto = projetoDTO.statusProjeto();
        this.orcamentoProjeto = projetoDTO.orcamentoProjeto();

    }

    public Projeto(int idProjeto, String nomeProjeto, String descricaoProjeto, String statusProjeto, double orcamentoProjeto){
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.descricaoProjeto = descricaoProjeto;
        this.statusProjeto = statusProjeto;
        this.orcamentoProjeto = orcamentoProjeto;
    }

    public Projeto(){}
}
