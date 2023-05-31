package br.com.alura.adopet.api_adopet.controller;

import br.com.alura.adopet.api_adopet.application.DTOs.usuario.CreateUsuarioDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.usuario.GetAllUsuarioDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.usuario.GetUsuarioDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
import br.com.alura.adopet.api_adopet.domain.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CreateUsuarioDTO dados, UriComponentsBuilder uriBuilder) {
        var usuario = usuarioService.cadastrar(dados);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new GetUsuarioDTO(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<GetAllUsuarioDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"})
                                                            Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(GetAllUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var abrigo = repository.getReferenceById(id);
        return ResponseEntity.ok(new GetUsuarioDTO(abrigo));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UpdateUsuarioDTO dados) {
        var abrigo = repository.getReferenceById(dados.id());
        abrigo.atualizarInformacoes(dados);
        return ResponseEntity.ok(new GetUsuarioDTO(abrigo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var abrigo = repository.getReferenceById(id);
        abrigo.excluir();
        return ResponseEntity.noContent().build();
    }
}
