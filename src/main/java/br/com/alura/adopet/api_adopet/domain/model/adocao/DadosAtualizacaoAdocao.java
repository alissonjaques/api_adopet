package br.com.alura.adopet.api_adopet.domain.model.adocao;

import br.com.alura.adopet.api_adopet.domain.model.usuario.Usuario;
import br.com.alura.adopet.api_adopet.domain.model.pet.Pet;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAdocao(
        @NotNull
        Long id,
        Pet pet,
        Usuario tutor) {
}