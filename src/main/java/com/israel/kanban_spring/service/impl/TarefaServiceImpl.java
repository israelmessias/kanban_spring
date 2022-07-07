package com.israel.kanban_spring.service.impl;

import com.israel.kanban_spring.error.TarefaErro;
import com.israel.kanban_spring.model.dto.TarefaCreateDTO;
import com.israel.kanban_spring.model.entity.Projeto;
import com.israel.kanban_spring.model.entity.Status;
import com.israel.kanban_spring.model.entity.Usuario;
import com.israel.kanban_spring.service.interfaces.TarefaService;
import com.israel.kanban_spring.model.dto.TarefaUpdateDTO;
import com.israel.kanban_spring.model.entity.Tarefa;
import com.israel.kanban_spring.model.enums.StatusEnum;
import com.israel.kanban_spring.model.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TarefaServiceImpl implements TarefaService {

    private TarefaRepository repository;

    private StatusServiceImpl statusService;

    private UsuarioServiceImpl usuarioService;

    private ProjetoServiceImpl projetoService;

    @Autowired
    public TarefaServiceImpl(@Lazy TarefaRepository repository, StatusServiceImpl statusService,
                             UsuarioServiceImpl usuarioService, @Lazy ProjetoServiceImpl projetoService){
        this.repository = repository;
        this.statusService = statusService;
        this.usuarioService = usuarioService;
        this.projetoService = projetoService;
    }

    @Override
    public Set<Tarefa> obterTarefas(Integer id) {

        List<Tarefa> tarefas = null;
        
        try {
            if(id == null){
              tarefas = repository.findAll();
            }else{
                tarefas = repository.findByIdTarefa(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Set tarefaSet = Set.copyOf(tarefas);

      return tarefaSet;
    }

    @Override
    public Tarefa criar(Tarefa tarefa) {
        try {
            Tarefa tarefaSaved = repository.saveAndFlush(tarefa);
            return tarefaSaved;
            
        } catch (Exception e) {
            throw new TarefaErro("Não foi possivel criar a tarefa.\n motivo: "+e.getMessage());
        }
    }

    @Override
    @Transactional
    public Tarefa atualizar(Tarefa tarefa) {

        try {
            Objects.requireNonNull(tarefa.getId());
            return repository.save(tarefa);
        } catch (Exception e) {
            throw new TarefaErro("Não foi possivel atualizar a tarefa. \n Motivo: "+e.getMessage());
        }
    }

    @Override
    public void atualizarStatus(Tarefa tarefa, StatusEnum status) {

    }

    @Override
    public Tarefa converterUpdate(TarefaUpdateDTO dto) {
        return null;
    }

    @Override
    public Tarefa converterCreate(TarefaCreateDTO dto) {
        Tarefa tarefa = new Tarefa();

        tarefa.setId(dto.getId());
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrevisaoInicial(dto.getPrevisaoInicial());
        tarefa.setPrivesiaoFinal(dto.getPrivesiaoFinal());
        tarefa.setCreateDate(new Date());
        tarefa.setUpdateDate(new Date());

        Optional<Status> status = statusService.obterStatusPorSigla(StatusEnum.AFAZER);
        tarefa.setStatus(status.get());
        Optional<Usuario>usuario = usuarioService.obterPorId(dto.getUsuario());
        tarefa.setUsuario(usuario.get());
        Optional<Projeto> projeto = projetoService.obterPorId(dto.getProjeto());
        tarefa.setProjeto(projeto.get());

        return tarefa;
    }


}
