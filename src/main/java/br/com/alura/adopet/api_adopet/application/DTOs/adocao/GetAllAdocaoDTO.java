package br.com.alura.adopet.api_adopet.application.DTOs.adocao;

import br.com.alura.adopet.api_adopet.domain.model.Adocao;
import br.com.alura.adopet.api_adopet.domain.model.Pet;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;

import java.time.LocalDateTime;

public record GetAllAdocaoDTO(Long id, Pet pet, Usuario tutor, LocalDateTime data) {
    public GetAllAdocaoDTO(Adocao adocao){
        this(
                adocao.getId(),
                new Pet(adocao.getPet().getId(),adocao.getAtivo(),adocao.getPet().getNome(),adocao.getPet().getDescricao(),
                        adocao.getPet().getAdotado(),adocao.getPet().getIdade(),adocao.getPet().getImagem(),
                        new Usuario(adocao.getPet().getUsuario().getId(),adocao.getPet().getUsuario().getNome(),
                                adocao.getPet().getUsuario().getEmail())),
                new Usuario(adocao.getTutor().getId(),adocao.getTutor().getNome(),adocao.getTutor().getEmail()),adocao.getData()
        );
    }
}
