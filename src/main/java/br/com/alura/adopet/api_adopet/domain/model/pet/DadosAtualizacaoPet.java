package br.com.alura.adopet.api_adopet.domain.model.pet;

import br.com.alura.adopet.api_adopet.domain.model.abrigo.DadosAtualizacaoAbrigo;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPet(
        @NotNull
        Long id,
        String nome,
        String descricao,
        Integer idade,
        String imagem,
        DadosAtualizacaoAbrigo abrigo) {
}