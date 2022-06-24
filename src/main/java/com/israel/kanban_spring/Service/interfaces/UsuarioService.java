package com.israel.kanban_spring.Service.interfaces;

import java.util.Optional;

import com.israel.kanban_spring.model.dto.UsuarioDTO;
import com.israel.kanban_spring.model.entity.Usuario;

public interface UsuarioService {
    Usuario salvar(Usuario usuario);

    Usuario atualizar(Usuario usuario);

    void deletar(Usuario usuario);

    Usuario autenticar(String email, String senha);

    void validarEmail(String email);

    Usuario conveter(UsuarioDTO usuarioDTO);
    
    Optional<Usuario> obterPorId(Integer id);
}

