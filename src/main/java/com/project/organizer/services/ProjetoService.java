package com.project.organizer.services;

import com.project.organizer.dto.ProjetoDTO;
import com.project.organizer.models.Projeto;
import com.project.organizer.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;


    public Projeto adicionarProjeto(ProjetoDTO projetoDTO){
        Projeto projeto = new Projeto(projetoDTO);

       return this.projetoRepository.save(projeto);

    }

    public boolean verificaSeExisteProjeto(ProjetoDTO projetoDTO){

        Optional<List<Projeto>> verificaProjeto = this.projetoRepository.findProjetoByNomeProjeto(projetoDTO.nomeProjeto());
        if(verificaProjeto.isPresent()){
            return true;
        }
        return false;
    }

    public List<Projeto> retornaListaDeProjetos(){
        List<Projeto> ltProjetos = this.projetoRepository.findAll();
        return ltProjetos;
    }

    public Optional<List<Projeto>> buscarProjetosPorNome(ProjetoDTO projetoDTO){

        boolean existe = verificaSeExisteProjeto(projetoDTO);
       Optional<List<Projeto>> ltProjeto = this.projetoRepository.findProjetoByNomeProjeto(projetoDTO.nomeProjeto());
        return ltProjeto;
    }
}
