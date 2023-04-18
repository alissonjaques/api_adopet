package br.com.alura.adopet.api_adopet.domain.model.usuario;

import br.com.alura.adopet.api_adopet.domain.model.enums.Perfil;
import jakarta.validation.constraints.NotNull;
import br.com.alura.adopet.api_adopet.domain.model.endereco.DadosEndereco;

public record DadosAtualizacaoUsuario(
        @NotNull
        Long id,
        Perfil perfil,
        String nome,
        String email,
        String senha,
        String confirmacaoSenha,
        String telefone,
        String sobre,
        DadosEndereco endereco) {
}