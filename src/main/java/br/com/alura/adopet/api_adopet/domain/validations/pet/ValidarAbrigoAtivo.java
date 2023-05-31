package br.com.alura.adopet.api_adopet.domain.validations.pet;

import br.com.alura.adopet.api_adopet.domain.exceptions.ValidacaoException;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarAbrigoAtivo implements IPetValidation{
    @Autowired
    UsuarioRepository usuarioRepository;
    public void validar(Usuario abrigo){
        usuarioRepository.getReferenceById(abrigo.getId());
        if(!abrigo.getAtivo()){
            throw new ValidacaoException("Não foi possível cadastrar o pet, abrigo não encontrado.");
        }
    }
}
