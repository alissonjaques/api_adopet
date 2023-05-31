package br.com.alura.adopet.api_adopet.domain.services;

import br.com.alura.adopet.api_adopet.application.DTOs.pet.CreatePetDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.pet.UpdatePetDTO;
import br.com.alura.adopet.api_adopet.domain.interfaces.PetRepository;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
import br.com.alura.adopet.api_adopet.domain.model.Pet;
import br.com.alura.adopet.api_adopet.domain.validations.pet.IPetValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    List<IPetValidation> validacoes;

    public Pet cadastrar(CreatePetDTO createPetDTO){
        var abrigo = usuarioRepository.getReferenceById(createPetDTO.usuario().getId());
        validacoes.forEach(v -> v.validar(abrigo));
        var pet = new Pet(createPetDTO);
        petRepository.save(pet);
        return pet;
    }

    public Pet atualizar(UpdatePetDTO dados) {
        if(dados.usuario()!=null){
            var abrigo = usuarioRepository.getReferenceById(dados.usuario().getId());
            validacoes.forEach(v -> v.validar(abrigo));
        }
        var pet = petRepository.getReferenceById(dados.id());
        pet.atualizarInformacoes(dados);
        return pet;
    }
}
