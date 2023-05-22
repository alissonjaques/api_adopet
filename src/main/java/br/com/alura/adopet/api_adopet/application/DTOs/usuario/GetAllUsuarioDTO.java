package br.com.alura.adopet.api_adopet.application.DTOs.usuario;

import br.com.alura.adopet.api_adopet.domain.enums.Perfil;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;

public record GetAllUsuarioDTO(Long id, Perfil perfil, String nome, String email, String sobre) {
    public GetAllUsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getPerfil(), usuario.getNome(), usuario.getEmail(), usuario.getSobre());
    }
}
