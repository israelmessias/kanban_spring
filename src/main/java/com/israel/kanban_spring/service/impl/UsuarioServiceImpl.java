package com.israel.kanban_spring.service.impl;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import com.israel.kanban_spring.model.dto.UsuarioUpdateDTO;
import com.israel.kanban_spring.model.enums.NivelAcessoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.israel.kanban_spring.error.UsuarioErro;
import com.israel.kanban_spring.service.interfaces.UsuarioService;
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
            throw new UsuarioErro("Não Conseguio salvar usuario, motivo : "+ e.getMessage());
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
            throw new UsuarioErro("Nao foi possivel deletar o usuario, motivo: " + e.getMessage());
            //TODO: handle exception
        }
    }

    @Override
    public Usuario autenticar(String email, String senha) {

        Optional<Usuario> usuario  = repository.findByEmail(email);

        if(!usuario.isPresent()){

            throw new UsuarioErro("Usuario não Encontrado");
        }

        boolean senhaValidada = passwordEncoder().matches(senha, usuario.get().getSenha());


        if(!senhaValidada){

            throw new UsuarioErro("Senha não correspondem!");
        }
        return usuario.get();
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

        NivelAcesso nivelAcesso = nivelAcessoService.obterPorSigla(usuarioDTO.getNivelAcesso()).
            orElseThrow( () -> new UsuarioErro("Nivel de Acesso não encontrado para o Id informado.") );

        usuario.setNivelAcesso(nivelAcesso);

        return usuario;
    }

    @Override
    public Usuario conveterUpdate(UsuarioUpdateDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setData(new Date());

        return usuario;
    }

    @Override
	public Optional<Usuario> obterPorId(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

    @Override
    @Transactional
    public Usuario atualizarNivelAcesso(Usuario scrumMaster, Integer usuario, NivelAcessoEnum enums) {
        Optional<Usuario> users = repository.findById(usuario);
        Optional<NivelAcesso> nivelAcesso = nivelAcessoService.obterPorSigla(enums);

        if(scrumMaster.getNivelAcesso().getNivelAcessoEnum().equals(NivelAcessoEnum.SM)){
            users.get().setNivelAcesso(nivelAcesso.get());
        }
        return atualizar(users.get());
    }


}
