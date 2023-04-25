package br.com.alura.adopet.api_adopet.controller;

import br.com.alura.adopet.api_adopet.application.DTOs.usuario.CreateUsuario;
import br.com.alura.adopet.api_adopet.application.DTOs.usuario.GetAllUsuario;
import br.com.alura.adopet.api_adopet.application.DTOs.usuario.GetUsuario;
import br.com.alura.adopet.api_adopet.application.DTOs.usuario.UpdateUsuario;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CreateUsuario dados, UriComponentsBuilder uriBuilder) {
        if (!dados.senha().equals(dados.confirmacaoSenha())) {
            return ResponseEntity.badRequest().body("A senha e sua confirmação não conferem!");
        }
        var abrigo = new Usuario(dados);
        repository.save(abrigo);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(abrigo.getId()).toUri();
        return ResponseEntity.created(uri).body(new GetUsuario(abrigo));
    }

    @GetMapping
    public ResponseEntity<Page<GetAllUsuario>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"})
                                                            Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(GetAllUsuario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var abrigo = repository.getReferenceById(id);
        return ResponseEntity.ok(new GetUsuario(abrigo));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UpdateUsuario dados) {
        var abrigo = repository.getReferenceById(dados.id());
        abrigo.atualizarInformacoes(dados);
        return ResponseEntity.ok(new GetUsuario(abrigo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var abrigo = repository.getReferenceById(id);
        abrigo.excluir();
        return ResponseEntity.noContent().build();
    }
}
