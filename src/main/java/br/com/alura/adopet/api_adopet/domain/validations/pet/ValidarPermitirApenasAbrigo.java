package br.com.alura.adopet.api_adopet.domain.validations.pet;

import br.com.alura.adopet.api_adopet.domain.exceptions.ValidacaoException;
import br.com.alura.adopet.api_adopet.domain.enums.Perfil;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ValidarPermitirApenasAbrigo implements IPetValidation {
    public void validar(Usuario abrigo){
        if(abrigo.getPerfil().equals(Perfil.TUTOR)){
            throw new ValidacaoException("Não foi possível cadastrar o pet, pois o pet deve pertencer a um abrigo e não " +
                    "a um tutor.");
        }
    }
}
