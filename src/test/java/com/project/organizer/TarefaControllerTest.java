package com.project.organizer;


import com.project.organizer.controllers.TarefasController;
import com.project.organizer.dto.TarefasDTO;
import com.project.organizer.models.Projeto;
import com.project.organizer.models.Tarefas;
import com.project.organizer.services.TarefasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TarefaControllerTest {

    @Mock
    private TarefasService tarefasService;

    @InjectMocks
    private TarefasController tarefasController;

    private TarefasDTO tarefasDTO;
    private Tarefas tarefa;

    @BeforeEach
    public void setUp() {
        tarefasDTO = new TarefasDTO("Tarefa teste", 1, LocalDate.now(), LocalDate.now().plusDays(1),0,"Andamento");
        Projeto projeto = new Projeto(1, "Projeto A", "Descrição do Projeto A","Andamento",10);
        Tarefas tarefa1 = new Tarefas();

        tarefa = new Tarefas();
        tarefa.setIdTarefa(1);
        tarefa.setDescricaoTarefa("Tarefa Teste");
        tarefa.setProjeto(projeto);
        tarefa.setDataInicio(LocalDate.now());
        tarefa.setDataFim(LocalDate.now().plusDays(1));
        tarefa.setTarefaPredecedora(tarefa1);
        tarefa.setStatusTarefa("PENDENTE");
    }

    @Test
    public void testSalvarTarefas() {
        when(tarefasService.salvarTarefas(any(TarefasDTO.class))).thenReturn(tarefa);

        ResponseEntity<Tarefas> response = tarefasController.salvarTarefas(tarefasDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Tarefa Teste", response.getBody().getDescricaoTarefa());
    }

    @Test
    public void testRetornarTodasTarefas() {
        List<Tarefas> tarefasList = Arrays.asList(tarefa);
        when(tarefasService.retornarTodasTarefas()).thenReturn(tarefasList);

        ResponseEntity<List<Tarefas>> response = tarefasController.retornarTodasTarefas();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Tarefa Teste", response.getBody().get(0).getDescricaoTarefa());
    }

    @Test
    public void testAtualizarTarefasPorId() {
        TarefasDTO updatedDTO = new TarefasDTO("Tarefa teste", 1, LocalDate.now(), LocalDate.now().plusDays(1),0,"Andamento");;


        Tarefas updatedTarefa = new Tarefas();
        updatedTarefa.setIdTarefa(1);
        updatedTarefa.setDescricaoTarefa("Tarefa Atualizada");

        when(tarefasService.atualizarTarefas(eq(1), any(TarefasDTO.class))).thenReturn(updatedTarefa);

        ResponseEntity<Tarefas> response = tarefasController.atualizarTarefasPorId(1, updatedDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Tarefa Atualizada", response.getBody().getDescricaoTarefa());
    }

    @Test
    public void testBuscarTarefasPorNome() {
        List<Tarefas> tarefasList = Arrays.asList(tarefa);
        when(tarefasService.buscarTarefasPorNome("Tarefa Teste")).thenReturn(tarefasList);

        ResponseEntity<List<Tarefas>> response = tarefasController.buscarTarefasPorNome("Tarefa Teste");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Tarefa Teste", response.getBody().get(0).getDescricaoTarefa());
    }

    @Test
    public void testExcluirTarefaPorID() {
        doNothing().when(tarefasService).excluirTarefaPorID(1);

        ResponseEntity<Tarefas> response = tarefasController.excluirTarefaPorID(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(tarefasService, times(1)).excluirTarefaPorID(1);
    }

}
