package br.com.alura.adopet.api_adopet.application.DTOs.pet;

import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePet(
        @NotBlank()
        String nome,
        @NotBlank()
        String descricao,
        @NotNull()
        Integer idade,
        @NotBlank
        String imagem,
        @NotNull()
        Usuario usuario) {}