package com.project.organizer.controllers;

import com.project.organizer.dto.TarefasDTO;
import com.project.organizer.models.Tarefas;
import com.project.organizer.services.TarefasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefasService tarefasService;

    // Adiciona tarefa
    @Operation(summary = "Criação de uma nova Tarefa")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso")
    @PostMapping
    public ResponseEntity<Tarefas> salvarTarefas(@Valid @RequestBody TarefasDTO tarefasDTO){
        Tarefas t = this.tarefasService.salvarTarefas(tarefasDTO);
        return ResponseEntity.ok(t);
    }

    // Lista todos os tarefa
    @Operation(summary = "Retorna todas as Tarefas", description = "Retornou todas as tarefas")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @GetMapping
    public ResponseEntity<List<Tarefas>> retornarTodasTarefas(){
        List<Tarefas> ltTarefas = this.tarefasService.retornarTodasTarefas();
        return ResponseEntity.ok(ltTarefas);
    }


    // Atualiza tarefa por id
    @Operation(summary = "Atualiza uma tarefa existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{idtarefa}")
    public ResponseEntity<Tarefas> atualizarTarefasPorId(@PathVariable int idtarefa, @RequestBody TarefasDTO tarefasDTO){
        var tarefa = this.tarefasService.atualizarTarefas(idtarefa, tarefasDTO);
        return ResponseEntity.ok(tarefa);
    }


    // Busca tarefa por nome
    @Operation(summary = "Busca tarefa por Nome")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Tarefa encontrada"),
                    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
            })
    @GetMapping("/{valor}")
    public ResponseEntity<List<Tarefas>> buscarTarefasPorNome(@PathVariable String valor){
        List<Tarefas> ltTarefas = this.tarefasService.buscarTarefasPorNome(valor);
        return ResponseEntity.ok(ltTarefas);

    }



    // Remove tarefa por ID
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Remove Tarefa por ID"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{idTarefa}")
    public ResponseEntity<Tarefas> excluirTarefaPorID(@PathVariable("idTarefa") int idTarefa){
        this.tarefasService.excluirTarefaPorID(idTarefa);
        return ResponseEntity.noContent().build();
    }
}
