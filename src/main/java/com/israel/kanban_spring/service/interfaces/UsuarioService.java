package com.israel.kanban_spring.service.interfaces;

import java.util.Optional;

import com.israel.kanban_spring.model.dto.UsuarioDTO;
import com.israel.kanban_spring.model.dto.UsuarioUpdateDTO;
import com.israel.kanban_spring.model.entity.Usuario;
import com.israel.kanban_spring.model.enums.NivelAcessoEnum;

public interface UsuarioService {
    Usuario salvar(Usuario usuario);

    Usuario atualizar(Usuario usuario);

    void deletar(Usuario usuario);

    Usuario autenticar(String email, String senha);

    Usuario conveter(UsuarioDTO usuarioDTO);
    Usuario conveterUpdate(UsuarioUpdateDTO usuarioDTO);

    Optional<Usuario> obterPorId(Integer id);

    Usuario atualizarNivelAcesso(Usuario scrumMaster, Integer usuario, NivelAcessoEnum enums);
}

