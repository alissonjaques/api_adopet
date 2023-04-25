package br.com.alura.adopet.api_adopet.application.DTOs.adocao;

import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import br.com.alura.adopet.api_adopet.domain.model.Pet;
import jakarta.validation.constraints.NotNull;

public record UpdateAdocao(
        @NotNull
        Long id,
        Pet pet,
        Usuario tutor) {
}