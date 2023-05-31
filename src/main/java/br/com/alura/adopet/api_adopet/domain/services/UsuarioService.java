package br.com.alura.adopet.api_adopet.domain.services;

import br.com.alura.adopet.api_adopet.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import br.com.alura.adopet.api_adopet.domain.validations.usuario.IUsuarioValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    List<IUsuarioValidation> validacoes;

    public Usuario cadastrar(CreateUsuarioDTO createUsuarioDTO){
        validacoes.forEach(v -> v.validar(createUsuarioDTO));
        var usuario = new Usuario(createUsuarioDTO);
        usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
        repository.save(usuario);
        return usuario;
    }
}
