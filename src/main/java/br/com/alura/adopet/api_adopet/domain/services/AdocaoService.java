package br.com.alura.adopet.api_adopet.domain.services;

import br.com.alura.adopet.api_adopet.application.DTOs.adocao.CreateAdocaoDTO;
import br.com.alura.adopet.api_adopet.domain.interfaces.AdocaoRepository;
import br.com.alura.adopet.api_adopet.domain.interfaces.PetRepository;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
import br.com.alura.adopet.api_adopet.domain.model.Adocao;
import br.com.alura.adopet.api_adopet.domain.validations.adocao.IAdocaoPetValidation;
import br.com.alura.adopet.api_adopet.domain.validations.adocao.IAdocaoTutorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdocaoService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    UsuarioRepository tutorRepository;
    @Autowired
    AdocaoRepository adocaoRepository;
    @Autowired
    List<IAdocaoPetValidation> petValidacoes;
    @Autowired
    List<IAdocaoTutorValidation> tutorValidacoes;
    public Adocao cadastrar(CreateAdocaoDTO dados) {
        var pet = petRepository.getReferenceById(dados.pet().getId());
        petValidacoes.forEach(v -> v.validar(pet));

        var tutor = tutorRepository.getReferenceById(dados.tutor().getId());
        tutorValidacoes.forEach(v -> v.validar(tutor));

        pet.adotar();
        var adocao = new Adocao(dados);
        adocaoRepository.save(adocao);
        return adocao;
    }
}
