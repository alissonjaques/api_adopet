package br.com.alura.adopet.api_adopet.application.DTOs.pet;

import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record UpdatePetDTO(
        @NotNull
        Long id,
        String nome,
        String descricao,
        Integer idade,
        String imagem,
        Usuario usuario) {
}