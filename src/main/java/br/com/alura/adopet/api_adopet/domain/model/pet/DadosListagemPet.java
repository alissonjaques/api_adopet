package br.com.alura.adopet.api_adopet.domain.model.pet;

import br.com.alura.adopet.api_adopet.domain.model.abrigo.Abrigo;

public record DadosListagemPet(Long id, String nome, String descricao, Integer idade, String imagem, Abrigo abrigo) {
    public DadosListagemPet(Pet pet){
        this(pet.getId(), pet.getNome(), pet.getDescricao(), pet.getIdade(), pet.getImagem(), pet.getAbrigo());
    }
}
