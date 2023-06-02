package br.com.alura.adopet.api_adopet.application.DTOs.pet;

import br.com.alura.adopet.api_adopet.domain.model.Pet;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;

public record GetPetDTO(Long id, String nome, String descricao, Boolean adotado, Integer idade, String imagem,
                        Usuario usuario) {
    public GetPetDTO(Pet pet){
        this(pet.getId(),pet.getNome(),pet.getDescricao(),pet.getAdotado(),pet.getIdade(),pet.getImagem(),
                new Usuario(pet.getUsuario().getId(),pet.getUsuario().getNome(),pet.getUsuario().getEmail()));
    }
}