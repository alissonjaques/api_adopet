package br.com.alura.adopet.api_adopet.application.DTOs.usuario;

import br.com.alura.adopet.api_adopet.domain.enums.Perfil;
import jakarta.validation.constraints.NotNull;
import br.com.alura.adopet.api_adopet.application.DTOs.endereco.Endereco;

public record UpdateUsuario(
        @NotNull
        Long id,
        Perfil perfil,
        String nome,
        String email,
        String senha,
        String confirmacaoSenha,
        String telefone,
        String sobre,
        Endereco endereco) {
}