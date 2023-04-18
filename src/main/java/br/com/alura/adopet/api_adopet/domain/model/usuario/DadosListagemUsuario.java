package br.com.alura.adopet.api_adopet.domain.model.usuario;

import br.com.alura.adopet.api_adopet.domain.model.enums.Perfil;

public record DadosListagemUsuario(Long id, Perfil perfil, String nome, String email, String sobre) {
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getPerfil(), usuario.getNome(), usuario.getEmail(), usuario.getSobre());
    }
}
