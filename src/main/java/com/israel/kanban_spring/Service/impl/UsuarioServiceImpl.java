package com.israel.kanban_spring.Service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.israel.kanban_spring.Err.UsuarioErro;
import com.israel.kanban_spring.Service.interfaces.UsuarioService;
import com.israel.kanban_spring.model.dto.UsuarioDTO;
import com.israel.kanban_spring.model.entity.NivelAcesso;
import com.israel.kanban_spring.model.entity.Usuario;
import com.israel.kanban_spring.model.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private NivelAcessoServiceImpl nivelAcessoService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UsuarioServiceImpl(UsuarioRepository repository, NivelAcessoServiceImpl nivelAcessoService){
        super();
        this.repository = repository;
        this.nivelAcessoService = nivelAcessoService;
}

    @Override
    @Transactional
    public Usuario salvar(Usuario usuario) {

        try {
            criptografarSenha(usuario);
            Usuario usuarioSave = repository.save(usuario);
            return usuarioSave;
        } catch (Exception e) {
            throw new UsuarioErro("N�o Conseguio salvar usuario, motivo : "+ e.getMessage());
            //TODO: handle exception
        }
       
        // TODO Auto-generated method stub
        
    }

    private void criptografarSenha(Usuario usuario){
        String senha = passwordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senha);
    }

    @Override
    public Usuario atualizar(Usuario usuario) {

        try {
            Objects.requireNonNull(usuario.getId());
            criptografarSenha(usuario);
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
            throw new UsuarioErro("N�o foi possivel deletar o usuario, motivo: " + e.getMessage());
            //TODO: handle exception
        }
    }

    @Override
    public Usuario autenticar(String email, String senha) {

        Optional<Usuario> usuario  = repository.findByEmail(email);
        System.out.println(usuario.get().getEmail());

        if(!usuario.isPresent()){
            System.out.println("Email não correspondem");
            throw new UsuarioErro("Usuario não Encontrado");
        }

        boolean senhaValidada = passwordEncoder().matches(senha, usuario.get().getSenha());
        System.out.println(usuario.get().getSenha());

        if(!senhaValidada){
            System.out.println("Senhas não correspondem");
            throw new UsuarioErro("Senha não correspondem!");
        }

        System.out.println(usuario.get());
        return usuario.get();
    }

    @Override
    public void validarEmail(String email) {
        boolean existeEmail = repository.existsByEmail(email);

        if(existeEmail){
            throw new UsuarioErro("J� existe email cadastrado");
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

        System.out.println("Nive3l acesso - "+usuarioDTO.getNivelAcesso());

        NivelAcesso nivelAcesso = nivelAcessoService.obterPorId(usuarioDTO.getNivelAcesso()).
            orElseThrow( () -> new UsuarioErro("Nivel de Acesso não encontrado para o Id informado.") );

        usuario.setNivelAcesso(nivelAcesso);

        return usuario;
    }

	@Override
	public Optional<Usuario> obterPorId(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}
    
}
