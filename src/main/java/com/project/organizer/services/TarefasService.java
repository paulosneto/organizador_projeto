package com.project.organizer.services;

import com.project.organizer.models.Tarefas;
import com.project.organizer.repositories.TarefasRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefasService {

    @Autowired
    private TarefasRepository tarefasRepository;


    public Tarefas salvarTarefas(Tarefas tarefas){

        return this.tarefasRepository.save(tarefas);
    }

    public List<Tarefas> retornarTodasTarefas(){
        List<Tarefas> lt = this.tarefasRepository.findAll();
        return lt;
    }

    // CONTINUAR
    public Tarefas atualizarTarefas(){
        Tarefas t = new Tarefas();
        return t;
    }
}
