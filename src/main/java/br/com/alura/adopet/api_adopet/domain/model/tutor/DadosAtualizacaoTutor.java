package br.com.alura.adopet.api_adopet.domain.model.tutor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTutor(
        @NotNull
        Long id,
        String nome,
        String email,
        String senha,
        String confirmacaoSenha) {
}
