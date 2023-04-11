package br.com.alura.adopet.api_adopet.domain.model.pet;

import br.com.alura.adopet.api_adopet.domain.model.abrigo.Abrigo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPet(
        @NotBlank()
        String nome,
        @NotBlank()
        String descricao,
        @NotNull()
        Integer idade,
        @NotBlank
        String imagem,
        @NotNull()
        Abrigo abrigo) {}