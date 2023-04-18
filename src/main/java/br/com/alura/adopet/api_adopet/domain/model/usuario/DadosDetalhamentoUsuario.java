package br.com.alura.adopet.api_adopet.domain.model.usuario;

import br.com.alura.adopet.api_adopet.domain.model.endereco.Endereco;
import br.com.alura.adopet.api_adopet.domain.model.enums.Perfil;

public record DadosDetalhamentoUsuario(Long id, Perfil perfil, String nome, String email, String telefone, String sobre,
                                       Endereco endereco) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getPerfil(), usuario.getNome(), usuario.getEmail(), usuario.getTelefone(),
                usuario.getSobre(), usuario.getEndereco());
    }
}