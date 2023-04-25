package br.com.alura.adopet.api_adopet.application.DTOs.adocao;

import br.com.alura.adopet.api_adopet.domain.model.Pet;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record CreateAdocao(
        @NotNull()
        Pet pet,
        @NotNull()
        Usuario tutor
        ) {
}