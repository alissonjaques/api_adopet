package br.com.alura.adopet.api_adopet.domain.model.pet;

import br.com.alura.adopet.api_adopet.domain.model.usuario.Usuario;

public record DadosDetalhamentoPet(Long id, String nome, String descricao, Boolean adotado, Integer idade, String imagem,
                                   Usuario usuario) {
    public DadosDetalhamentoPet(Pet pet){
        this(pet.getId(),pet.getNome(),pet.getDescricao(),pet.getAdotado(),pet.getIdade(),pet.getImagem(),pet.getUsuario());
    }
}