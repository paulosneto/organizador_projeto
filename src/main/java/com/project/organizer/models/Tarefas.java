package com.project.organizer.models;

import com.project.organizer.dto.TarefasDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tarefas")
public class Tarefas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTarefa")
    private int idTarefa;
    @Column(name = "descricaoTarefa", nullable = false)
    private String descricaoTarefa;
    @Column(name = "projeto", nullable = false)
    private String projeto;
    @Column(name = "dataInicio")
    private LocalDateTime dataInicio;
    @Column(name = "dataFim")
    private LocalDateTime dataFim;
    @Column(name = "taferaPredecedora")
    private String tarefaPredecedora;
    @Column(name = "statusTarefa")
    private String statusTarefa;

    public Tarefas(TarefasDTO tarefasDTO){
        this.descricaoTarefa = tarefasDTO.descricaoTarefa();
        this.projeto = tarefasDTO.projeto();
        this.dataInicio = tarefasDTO.dataInicio();
        this.dataFim = tarefasDTO.dataFim();
        this.tarefaPredecedora = tarefasDTO.tarefaPredecedora();
        this.statusTarefa = tarefasDTO.statusTarefa();
    }

}
