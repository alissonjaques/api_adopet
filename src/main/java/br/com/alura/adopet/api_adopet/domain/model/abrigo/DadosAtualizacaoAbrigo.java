package br.com.alura.adopet.api_adopet.domain.model.abrigo;

import jakarta.validation.constraints.NotNull;
import br.com.alura.adopet.api_adopet.domain.model.endereco.DadosEndereco;

public record DadosAtualizacaoAbrigo(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String sobre,
        DadosEndereco endereco) {
}