package br.com.alura.adopet.api_adopet.domain.model.usuario;

import br.com.alura.adopet.api_adopet.domain.model.enums.Perfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import br.com.alura.adopet.api_adopet.domain.model.endereco.DadosEndereco;

public record DadosCadastroUsuario(
        @NotNull
        Perfil perfil,
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String confirmacaoSenha,
        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,
        String sobre,
        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        DadosEndereco endereco) {}