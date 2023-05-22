package br.com.alura.adopet.api_adopet.application.DTOs.adocao;

import br.com.alura.adopet.api_adopet.domain.model.Adocao;
import br.com.alura.adopet.api_adopet.domain.model.Pet;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;

import java.time.LocalDateTime;

public record GetAllAdocaoDTO(Long id, Pet pet, Usuario tutor, LocalDateTime data) {
    public GetAllAdocaoDTO(Adocao adocao){
        this(adocao.getId(), adocao.getPet(), adocao.getTutor(), adocao.getData());
    }
}
