package br.com.alura.adopet.api_adopet.application.DTOs.adocao;

import br.com.alura.adopet.api_adopet.domain.model.Adocao;
import br.com.alura.adopet.api_adopet.domain.model.Pet;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;

import java.time.LocalDateTime;

public record GetAdocao(Long id, Pet pet, Usuario tutor, LocalDateTime data) {
    public GetAdocao(Adocao adocao){
        this(adocao.getId(),adocao.getPet(),adocao.getTutor(),adocao.getData());
    }
}