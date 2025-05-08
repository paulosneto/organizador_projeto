package com.project.organizer.models;

import com.project.organizer.dto.TarefasDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarefas")
public class Tarefas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTarefa")
    private int idTarefa;
    @Column(name = "descricaoTarefa", nullable = false)
    private String descricaoTarefa;
    @ManyToOne
    @JoinColumn(name = "idprojeto")
    private Projeto projeto;
    @Column(name = "dataInicio")
    private LocalDate dataInicio;
    @Column(name = "dataFim")
    private LocalDate dataFim;
    @ManyToOne
    @JoinColumn(name = "idTaferaPredecedora")
    private Tarefas tarefaPredecedora;
    @Column(name = "statusTarefa")
    private String statusTarefa;

    public Tarefas(TarefasDTO tarefasDTO){
        this.descricaoTarefa = tarefasDTO.descricaoTarefa();

        if(tarefasDTO.idprojeto() != 0){
            this.projeto = new Projeto();
            this.projeto.setIdProjeto(tarefasDTO.idprojeto());
        }
        this.dataInicio = tarefasDTO.dataInicio();
        this.dataFim = tarefasDTO.dataFim();

        if(tarefasDTO.idTarefaPredecedora() != 0) {
            this.tarefaPredecedora = new Tarefas();
            this.tarefaPredecedora.setIdTarefa(tarefasDTO.idTarefaPredecedora());
        }
        this.statusTarefa = tarefasDTO.statusTarefa();
    }

}
