package com.project.organizer.models;

import com.project.organizer.dto.ProjetoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projeto")
public class Projeto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idprojeto")
    private int idProjeto;
    @Column(name = "nomeprojeto")
    private String nomeProjeto;
    @Column(name = "descricaoprojeto")
    private String descricaoProjeto;
    @Column(name = "statusprojeto")
    private String statusProjeto;
    @Column(name = "orcamentoprojeto")
    private double orcamentoProjeto;
    @Column(name = "datainicio")
    private Timestamp data_inicio;
    @Column(name = "datafim")
    private Timestamp data_fim;


    public Projeto(ProjetoDTO projetoDTO) {
        this.nomeProjeto = projetoDTO.nomeProjeto();
        this.descricaoProjeto = projetoDTO.descricaoProjeto();
        this.statusProjeto = projetoDTO.statusProjeto();
        this.orcamentoProjeto = projetoDTO.orcamentoProjeto();
        this.data_inicio = projetoDTO.dataInicio();
        this.data_fim = projetoDTO.dataFim();
    }


}
