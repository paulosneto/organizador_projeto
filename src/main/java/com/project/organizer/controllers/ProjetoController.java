package com.project.organizer.controllers;

import com.project.organizer.dto.ProjetoDTO;
import com.project.organizer.models.Projeto;
import com.project.organizer.services.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    // Adiciona projeto
    @Operation(summary = "Criação de um novo Projeto")
    @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso")
    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody ProjetoDTO dto){
        return ResponseEntity.ok(this.service.adicionarProjeto(dto));
    }

    // Lista todos os projetos
    @Operation(summary = "Retorna todos os Projetos", description = "Retornou todos os projetos")
    @ApiResponse(responseCode = "200", description = "Projetos encontrados")
    @GetMapping
    public ResponseEntity<List<Projeto>> retornarTodosProjetos(){
        return ResponseEntity.ok(this.service.retornaListaDeProjetos());
    }

    // Atualiza projeto por id
    @Operation(summary = "Atualiza um projeto existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projeto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{idprojeto}")
    public ResponseEntity<Projeto> atualizarProjetoPorId(@PathVariable int idprojeto, @RequestBody ProjetoDTO projetoDTO){
        var projeto = this.service.atualizarProjeto(idprojeto, projetoDTO);
        return ResponseEntity.ok(projeto);
    }

    // Busca projeto por nome ou descricao
    @Operation(summary = "Busca projeto por Nome ou Descrição")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Projeto encontrado"),
                    @ApiResponse(responseCode = "404", description = "Projeto não encontrado")
            })
    @GetMapping("/{valor}")
    public ResponseEntity<List<Projeto>> buscarProjetosPorNome(@PathVariable String valor){
        List<Projeto> ltProjetos = this.service.buscarProjetosPorNome(valor);
        return ResponseEntity.ok(ltProjetos);

    }

    // Remove projeto por ID
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Remove Projeto por ID"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado")
    })
    @DeleteMapping("/{idProjeto}")
    public ResponseEntity<Projeto> excluirProjetoPorID(@PathVariable("idProjeto") int idProjeto){
        this.service.excluirProjetoPorID(idProjeto);
        return ResponseEntity.noContent().build();
    }

}
