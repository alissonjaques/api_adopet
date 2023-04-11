package br.com.alura.adopet.api_adopet.domain.model.adocao;

import br.com.alura.adopet.api_adopet.domain.model.pet.Pet;
import br.com.alura.adopet.api_adopet.domain.model.tutor.Tutor;

import java.time.LocalDateTime;

public record DadosListagemAdocao(Long id, Pet pet, Tutor tutor, LocalDateTime data) {
    public DadosListagemAdocao(Adocao adocao){
        this(adocao.getId(), adocao.getPet(), adocao.getTutor(), adocao.getData());
    }
}
