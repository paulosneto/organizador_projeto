package com.project.organizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.organizer.controllers.ProjetoController;
import com.project.organizer.dto.ProjetoDTO;
import com.project.organizer.models.Projeto;
import com.project.organizer.services.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjetoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProjetoService projetoService;

    @InjectMocks
    private ProjetoController projetoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projetoController).build();
    }

    // adicionar projeto
    @Test
    public void testCriarProjeto() throws Exception {
        // Criação de dados fictícios
        ProjetoDTO projetoDTO = new ProjetoDTO("Projeto A", "Descrição do Projeto A","Andamento",10);
        Projeto projeto = new Projeto(1, "Projeto A", "Descrição do Projeto A","Andamento",10);

        // Mock do serviço
        when(projetoService.adicionarProjeto(projetoDTO)).thenReturn(projeto);

        // Teste do endpoint POST /projetos
        mockMvc.perform(post("/projetos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projetoDTO)))
                .andExpect(status().isOk())  // Verifica se o status é 200
                .andExpect(jsonPath("$.nomeProjeto").value("Projeto A"))  // Verifica o nome do projeto
                .andExpect(jsonPath("$.descricaoProjeto").value("Descrição do Projeto A"))  // Verifica a descrição
                .andExpect(jsonPath("$.statusProjeto").value("Andamento"))  // Verifica o andamento
                .andExpect(jsonPath("$.orcamentoProjeto").value(10));  // Verifica o orçamento
    }

    // testar o retorno de todos os projetos
    @Test
    public void testRetornarTodosProjetos() throws Exception {
        // Criação de dados fictícios
        Projeto projeto1 = new Projeto(1, "Projeto A", "Descrição do Projeto A","Andamento",10);


        // Mock do serviço
        when(projetoService.retornaListaDeProjetos()).thenReturn(List.of(projeto1));

        // Teste do endpoint GET /projetos
        mockMvc.perform(get("/projetos"))
                .andExpect(status().isOk())  // Verifica se o status é 200
                .andExpect(jsonPath("$[0].nomeProjeto").value("Projeto A"))  // Verifica o nome do projeto
                .andExpect(jsonPath("$[0].descricaoProjeto").value("Descrição do Projeto A"))  // Verifica a descrição
                .andExpect(jsonPath("$[0].statusProjeto").value("Andamento"))  // Verifica o andamento
                .andExpect(jsonPath("$[0].orcamentoProjeto").value(10));  // Verifica o orçamento
    }


    //Testar o metodo de atualizar
    @Test
    public void testAtualizarProjetoPorId() throws Exception {
        // Dados fictícios
        ProjetoDTO projetoDTO = new ProjetoDTO("Projeto A", "Descrição do Projeto", "Andamento", 10);
        Projeto projetoAtualizado = new Projeto(1, "Projeto A Atualizado", "Descrição Projeto Atualizada", "Concluida", 10);

        // Mock do serviço
        when(projetoService.atualizarProjeto(1, projetoDTO)).thenReturn(projetoAtualizado);

        // Teste do endpoint PUT /projetos/{idprojeto}
        mockMvc.perform(put("/projetos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(projetoDTO)))
                .andExpect(status().isOk())  // Verifica se o status é 200
                .andExpect(jsonPath("$.nomeProjeto").value("Projeto A Atualizado"))  // Verifica o nome do projeto atualizado
                .andExpect(jsonPath("$.descricaoProjeto").value("Descrição Projeto Atualizada"))  // Verifica a descrição
                .andExpect(jsonPath("$.statusProjeto").value("Concluida"))  // Verifica o status
                .andExpect(jsonPath("$.orcamentoProjeto").value(10));  // Verifica o orçamento
    }
    @Test
    public void testBuscarProjetosPorNome() throws Exception {
        // Dados fictícios
        Projeto projeto1 = new Projeto(1, "Projeto A", "Descrição A", "ANDAMENTO",150);
        Projeto projeto2 = new Projeto(2, "Projeto B", "Descrição B", "CONCLUIDO", 10);

        // Mock do serviço
        when(projetoService.buscarProjetosPorNome("Projeto")).thenReturn(List.of(projeto1, projeto2));

        // Teste do endpoint GET /projetos/{valor}
        mockMvc.perform(get("/projetos/Projeto"))
                .andExpect(status().isOk())  // Verifica se o status é 200
                .andExpect(jsonPath("$[0].nomeProjeto").value("Projeto A"))  // Verifica o primeiro projeto encontrado
                .andExpect(jsonPath("$[1].nomeProjeto").value("Projeto B"));  // Verifica o segundo projeto encontrado
    }


    @Test
    public void testExcluirProjetoPorID() throws Exception {
        // Teste do endpoint DELETE /projetos/{idProjeto}
        mockMvc.perform(delete("/projetos/1"))
                .andExpect(status().isNoContent());  // Verifica se o status é 204 (Sem conteúdo)
    }


}
