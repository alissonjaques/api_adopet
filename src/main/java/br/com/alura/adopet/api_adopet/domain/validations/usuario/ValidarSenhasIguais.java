package br.com.alura.adopet.api_adopet.domain.validations.usuario;


import br.com.alura.adopet.api_adopet.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.alura.adopet.api_adopet.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidarSenhasIguais implements IUsuarioValidation {
    public void validar(CreateUsuarioDTO createUsuarioDTO){
        if (!createUsuarioDTO.senha().equals(createUsuarioDTO.confirmacaoSenha())) {
            throw new ValidacaoException("A senha e sua confirmação não conferem!");
        }
    }
}
