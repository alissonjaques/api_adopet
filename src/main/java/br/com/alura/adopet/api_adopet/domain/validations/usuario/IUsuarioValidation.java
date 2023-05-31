package br.com.alura.adopet.api_adopet.domain.validations.usuario;

import br.com.alura.adopet.api_adopet.application.DTOs.usuario.CreateUsuarioDTO;

public interface IUsuarioValidation {
    void validar(CreateUsuarioDTO createUsuarioDTO);
}
