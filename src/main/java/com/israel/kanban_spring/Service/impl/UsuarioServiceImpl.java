package com.israel.kanban_spring.Service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.israel.kanban_spring.Err.UsuarioErro;
import com.israel.kanban_spring.Service.interfaces.UsuarioService;
import com.israel.kanban_spring.model.dto.UsuarioDTO;
import com.israel.kanban_spring.model.entity.NivelAcesso;
import com.israel.kanban_spring.model.entity.Usuario;
import com.israel.kanban_spring.model.repository.NivelAcessoRepository;
import com.israel.kanban_spring.model.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private NivelAcessoServiceImpl nivelAcessoService;

    public UsuarioServiceImpl(UsuarioRepository repository, NivelAcessoServiceImpl nivelAcessoService){
        super();
        this.repository = repository;
        this.nivelAcessoService = nivelAcessoService;
}

    // private PasswordEncoder encoder;

    @Override
    public Usuario salvar(Usuario usuario) {

        try {
            // criptografarSenha(usuario);
            Usuario usuarioSave = repository.save(usuario);
            return usuarioSave;
        } catch (Exception e) {
            throw new UsuarioErro("Não Conseguio salvar usuario, motivo : "+ e.getMessage());
            //TODO: handle exception
        }
       
        // TODO Auto-generated method stub
        
    }

    private void criptografarSenha(Usuario usuario){
        // String senha = encoder.encode(usuario.getSenha());
        // usuario.setSenha(senha);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {

        try {
            Objects.requireNonNull(usuario.getId());
            return repository.save(usuario);
        } catch (Exception e) {
            //TODO: handle exception
            throw new UsuarioErro("Não foi possivel atualizar o usuario, motivo: " + e.getMessage());
        }
    }

    @Override
    public void deletar(Usuario usuario) {

        try {
        Objects.requireNonNull(usuario.getId());
        repository.delete(usuario);
        } catch (Exception e) {
            throw new UsuarioErro("Não foi possivel deletar o usuario, motivo: " + e.getMessage());
            //TODO: handle exception
        }
    }

    @Override
    public Usuario autenticar(String email, String senha) {

        Optional<Usuario> usuario  = repository.findByEmail(email);

        if(!usuario.isPresent()){
            throw new UsuarioErro("Usuario não Encontrado");
        }

        // boolean senhaValidada = encoder.matches(senha, usuario.get().getSenha());

        // if(!senhaValidada){
        //     throw new UsuarioErro("Senha não correspondem!");
        // }

        return usuario.get();
    }

    @Override
    public void validarEmail(String email) {
        boolean existeEmail = repository.existsByEmail(email);

        if(existeEmail){
            throw new UsuarioErro("Já existe email cadastrado");
        }
        // TODO Auto-generated method stub
        
    }

   

    @Override
    public Usuario conveter(UsuarioDTO usuarioDTO) {
        // TODO Auto-generated method stub
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setData(new Date());

        NivelAcesso nivelAcesso = nivelAcessoService.obterPorId(usuarioDTO.getNivelAcesso()).
            orElseThrow( () -> new UsuarioErro("Nivel de Acesso não encontrado para o Id informado.") );

        usuario.setNivelAcesso(nivelAcesso);

        return usuario;
    }
    
}
