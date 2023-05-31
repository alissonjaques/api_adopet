package br.com.alura.adopet.api_adopet.domain.validations.adocao;

import br.com.alura.adopet.api_adopet.domain.exceptions.ValidacaoException;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarTutorAtivo implements IAdocaoTutorValidation {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void validar(Usuario tutor){
        usuarioRepository.getReferenceById(tutor.getId());
        if(!tutor.getAtivo()){
            throw new ValidacaoException("Não foi possível realizar a adoção, tutor informado não encontrado!");
        }
    }
}
