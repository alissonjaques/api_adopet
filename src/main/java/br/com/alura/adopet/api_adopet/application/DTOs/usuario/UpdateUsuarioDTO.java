package br.com.alura.adopet.api_adopet.application.DTOs.usuario;

import br.com.alura.adopet.api_adopet.domain.enums.Perfil;
import jakarta.validation.constraints.NotNull;
import br.com.alura.adopet.api_adopet.application.DTOs.endereco.EnderecoDTO;

public record UpdateUsuarioDTO(
        @NotNull
        Long id,
        Perfil perfil,
        String nome,
        String email,
        String senha,
        String confirmacaoSenha,
        String telefone,
        String sobre,
        EnderecoDTO endereco) {
}