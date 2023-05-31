package br.com.alura.adopet.api_adopet.domain.validations.adocao;

import br.com.alura.adopet.api_adopet.domain.exceptions.ValidacaoException;
import br.com.alura.adopet.api_adopet.domain.model.Pet;
import org.springframework.stereotype.Component;

@Component
public class ValidarPetAdotado implements IAdocaoPetValidation {
    public void validar(Pet pet) {
        if(pet.getAdotado()){
            throw  new ValidacaoException("Não foi possível realizar a adoção, pois o pet informado já foi adotado.");
        }
    }
}
