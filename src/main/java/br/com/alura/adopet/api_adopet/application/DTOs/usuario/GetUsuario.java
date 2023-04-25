package br.com.alura.adopet.api_adopet.application.DTOs.usuario;

import br.com.alura.adopet.api_adopet.domain.model.Endereco;
import br.com.alura.adopet.api_adopet.domain.enums.Perfil;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;

public record GetUsuario(Long id, Perfil perfil, String nome, String email, String telefone, String sobre,
                         Endereco endereco) {
    public GetUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getPerfil(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(),
                usuario.getSobre(), usuario.getEndereco());
    }
}