package com.project.organizer.models;

import com.project.organizer.dto.ProjetoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projeto")
public class Projeto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_projeto")
    private int idProjeto;
    @Column(name = "nome_projeto")
    private String nomeProjeto;
    @Column(name = "descricao_projeto")
    private String descricaoProjeto;
    @Column(name = "status_projeto")
    private String statusProjeto;
    @Column(name = "prcamento_projeto")
    private double orcamentoProjeto;


    public Projeto(ProjetoDTO projetoDTO) {
        this.nomeProjeto = projetoDTO.nomeProjeto();
        this.descricaoProjeto = projetoDTO.descricaoProjeto();
        this.statusProjeto = projetoDTO.statusProjeto();
        this.orcamentoProjeto = projetoDTO.orcamentoProjeto();
    }
}
