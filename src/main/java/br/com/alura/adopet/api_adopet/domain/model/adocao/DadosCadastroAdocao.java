package br.com.alura.adopet.api_adopet.domain.model.adocao;

import br.com.alura.adopet.api_adopet.domain.model.pet.Pet;
import br.com.alura.adopet.api_adopet.domain.model.tutor.Tutor;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAdocao(
        @NotNull()
        Pet pet,
        @NotNull()
        Tutor tutor
        ) {
}