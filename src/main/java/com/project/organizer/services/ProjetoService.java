package com.project.organizer.services;

import com.project.organizer.dto.ProjetoDTO;
import com.project.organizer.models.Projeto;
import com.project.organizer.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;


    public Projeto adicionarProjeto(ProjetoDTO projetoDTO){
        Projeto projeto = new Projeto(projetoDTO);

       return this.projetoRepository.save(projeto);

    }

    public boolean verificaSeExisteProjeto(ProjetoDTO projetoDTO){

        List<Projeto> verificaProjeto = this.projetoRepository.findProjetoByNomeProjetoContainingIgnoreCaseOrDescricaoProjetoContainingIgnoreCase(projetoDTO.nomeProjeto(), projetoDTO.descricaoProjeto());
//        if(verificaProjeto.isPresent()){
//            return true;
//        }
        return false;
    }

    public List<Projeto> retornaListaDeProjetos(){
        List<Projeto> ltProjetos = this.projetoRepository.findAll();
        return ltProjetos;
    }

    public List<Projeto> buscarProjetosPorNome(String valor){

        //boolean existe = verificaSeExisteProjeto(projetoDTO);

       List<Projeto> ltProjeto = this.projetoRepository.findProjetoByNomeProjetoContainingIgnoreCaseOrDescricaoProjetoContainingIgnoreCase(valor, valor);

       return ltProjeto;
    }

    /*public Projeto atualizarProjeto(int idprojeto, ProjetoDTO projetoDTO){

        Projeto novoProjeto = this.projetoRepository.findById(idprojeto).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

             novoProjeto.setNomeProjeto(projetoDTO.nomeProjeto());
             novoProjeto.setDescricaoProjeto(projetoDTO.descricaoProjeto());
             novoProjeto.setOrcamentoProjeto(projetoDTO.orcamentoProjeto());
             novoProjeto.setStatusProjeto(projetoDTO.statusProjeto());
             novoProjeto.setData_inicio(projetoDTO.dataInicio());
             novoProjeto.setData_fim(projetoDTO.dataFim());

            return this.projetoRepository.save(novoProjeto);
    }*/

    public Projeto atualizarProjeto(int idprojeto, ProjetoDTO projetoDTO){

        Projeto projetoAtualizado = null;
        var novoProjeto = this.projetoRepository.findById(idprojeto);

        if(novoProjeto.isPresent()){
            projetoAtualizado = new Projeto(projetoDTO);
            this.projetoRepository.save(projetoAtualizado);
        }
        return projetoAtualizado;
    }
    public void excluirProjetoPorID(int idProjeto){
        //var projeto = this.projetoRepository.findById(idProjeto).orElseThrow(() -> new RuntimeException("Não foi encontrado nenhum Projeto com o ID informado."));
        //this.projetoRepository.delete(projeto);

        this.projetoRepository.deleteById(idProjeto);

    }


}
