package br.com.alura.adopet.api_adopet.domain.model.pet;

import br.com.alura.adopet.api_adopet.domain.model.abrigo.Abrigo;

public record DadosDetalhamentoPet(Long id, String nome, String descricao, Boolean adotado, Integer idade, String imagem,
                                   Abrigo abrigo) {
    public DadosDetalhamentoPet(Pet pet){
        this(pet.getId(),pet.getNome(),pet.getDescricao(),pet.getAdotado(),pet.getIdade(),pet.getImagem(),pet.getAbrigo());
    }
}