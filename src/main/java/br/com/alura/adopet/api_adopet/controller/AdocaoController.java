package br.com.alura.adopet.api_adopet.controller;

import br.com.alura.adopet.api_adopet.application.DTOs.adocao.CreateAdocaoDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.adocao.GetAllAdocaoDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.adocao.GetAdocaoDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.adocao.UpdateAdocaoDTO;
import br.com.alura.adopet.api_adopet.domain.model.Adocao;
import br.com.alura.adopet.api_adopet.domain.interfaces.AdocaoRepository;
import br.com.alura.adopet.api_adopet.domain.interfaces.PetRepository;
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
@RequestMapping("adocoes")
@SecurityRequirement(name = "bearer-key")
public class AdocaoController {
    @Autowired
    private AdocaoRepository repository;
    @Autowired
    private PetRepository petRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CreateAdocaoDTO dados, UriComponentsBuilder uriBuilder) {
        var pet = petRepository.getReferenceById(dados.pet().getId());
        if(pet.getAdotado()){
            return ResponseEntity.badRequest().body("Não foi possível realizar a adoção," +
                    " pois o pet informado já foi adotado.");
        }
        pet.adotar();
        var adocao = new Adocao(dados);
        repository.save(adocao);
        var uri = uriBuilder.path("/adocoes/{id}").buildAndExpand(adocao.getId()).toUri();
        return ResponseEntity.created(uri).body(new GetAdocaoDTO(adocao));
    }

    @GetMapping
    public ResponseEntity<Page<GetAllAdocaoDTO>> listar(@PageableDefault(size = 10, page = 0, sort = {"pet"})
                                                         Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(GetAllAdocaoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var adocao = repository.getReferenceById(id);
        return ResponseEntity.ok(new GetAdocaoDTO(adocao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UpdateAdocaoDTO dados) {
        var adocao = repository.getReferenceById(dados.id());
        adocao.atualizarInformacoes(dados);
        return ResponseEntity.ok(new GetAdocaoDTO(adocao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var adocao = repository.getReferenceById(id);
        adocao.excluir();
        return ResponseEntity.noContent().build();
    }
}
