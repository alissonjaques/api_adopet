package br.com.alura.adopet.api_adopet.domain.validations.usuario;

import br.com.alura.adopet.api_adopet.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.alura.adopet.api_adopet.domain.exceptions.ValidacaoException;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarEmail implements IUsuarioValidation {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void validar(CreateUsuarioDTO createUsuarioDTO){
        var email = usuarioRepository.findEmail(createUsuarioDTO.email());
        if (createUsuarioDTO.email().equals(email)) {
            throw new ValidacaoException("Já existe usuário cadastrado com o email informado!");
        }
    }
}
