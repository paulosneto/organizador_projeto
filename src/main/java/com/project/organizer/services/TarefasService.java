package com.project.organizer.services;

import com.project.organizer.dto.ProjetoDTO;
import com.project.organizer.dto.TarefasDTO;
import com.project.organizer.exceptions.ExceptionCustomError;
import com.project.organizer.models.Projeto;
import com.project.organizer.models.Tarefas;
import com.project.organizer.repositories.ProjetoRepository;
import com.project.organizer.repositories.TarefasRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TarefasService {

    @Autowired
    private TarefasRepository tarefasRepository;
    @Autowired
    private ProjetoRepository projetoRepository;

    public Tarefas salvarTarefas(TarefasDTO tarefasDTO){

        if(tarefasDTO.dataFim().isBefore(tarefasDTO.dataInicio()) || tarefasDTO.dataInicio().isAfter(tarefasDTO.dataFim())){
            //throw new RuntimeException("A data fim não pode ser menor que a data inicial ou a data inicial não pode ser maior que a final");

            throw new ExceptionCustomError("A data fim não pode ser menor que a data inicial ou a data inicial não pode ser maior que a final", 404);
        }

        Projeto p = this.projetoRepository.findById(tarefasDTO.idprojeto()).orElseThrow(() -> new ExceptionCustomError("Projeto não encontrado",404));
        Tarefas tarefas =  new Tarefas(tarefasDTO);
            tarefas.setProjeto(p);

            if(tarefasDTO.idTarefaPredecedora() != 0){
                Tarefas t = this.tarefasRepository.findById(tarefasDTO.idTarefaPredecedora()).orElseThrow(() -> new ExceptionCustomError("Tarefa não encontrada",404));
                    tarefas.setTarefaPredecedora(t);
            }


        return this.tarefasRepository.save(tarefas);
    }

    public List<Tarefas> retornarTodasTarefas(){
        List<Tarefas> lt = this.tarefasRepository.findAll();
        return lt;
    }

    public List<Tarefas> buscarTarefasPorNome(String valor){

        List<Tarefas> ltProjeto = this.tarefasRepository.findTarefaByDescricaoTarefaContainingIgnoreCase(valor);

        return ltProjeto;
    }


    // CONTINUAR
    /*public Tarefas atualizarTarefas(int idTarefa, TarefasDTO tarefasDTO){
        Tarefas tarefaAtualizada = null;
        var tarefa = this.tarefasRepository.findById(idTarefa);

        if(tarefa.isPresent()){
            tarefaAtualizada = new Tarefas(tarefasDTO);
            this.tarefasRepository.save(tarefaAtualizada);
        }else{
            throw  new ExceptionCustomError("Não foi possível atualizar a tarefa escolhida", 400);
        }
        return tarefaAtualizada;
    }*/

    public Tarefas atualizarTarefas(int idTarefa, TarefasDTO tarefasDTO){
        Tarefas tarefaExistente = tarefasRepository.findById(idTarefa)
                .orElseThrow(() -> new ExceptionCustomError("Tarefa não encontrada",404));

        // Atualiza os campos
        tarefaExistente.setDescricaoTarefa(tarefasDTO.descricaoTarefa());
        tarefaExistente.setDataInicio(tarefasDTO.dataInicio());
        tarefaExistente.setDataFim(tarefasDTO.dataFim());
        tarefaExistente.setStatusTarefa(tarefasDTO.statusTarefa());

        // Atualiza o projeto
        Projeto projeto = projetoRepository.findById(tarefasDTO.idprojeto())
                .orElseThrow(() -> new EntityNotFoundException("Projeto com ID " + tarefasDTO.idprojeto() + " não encontrado"));
        tarefaExistente.setProjeto(projeto);

        // Atualiza a tarefa predecessora (se houver)
        if (tarefasDTO.idTarefaPredecedora() != null) {
            Tarefas predecessora = tarefasRepository.findById(tarefasDTO.idTarefaPredecedora())
                    .orElseThrow(() -> new EntityNotFoundException("Tarefa predecessora com ID " + tarefasDTO.idTarefaPredecedora() + " não encontrada"));
            tarefaExistente.setTarefaPredecedora(predecessora);
        } else {
            tarefaExistente.setTarefaPredecedora(null); // remove caso não enviado
        }

        return tarefasRepository.save(tarefaExistente);
    }

    public void excluirTarefaPorID(int idTarefa){
        var tarefa = this.tarefasRepository.findById(idTarefa).orElseThrow(() -> new ExceptionCustomError("Não foi encontrado nenhuma Tarefa com o ID informado.", 404));
        this.tarefasRepository.delete(tarefa);

        //this.projetoRepository.deleteById(idProjeto);

    }
}
