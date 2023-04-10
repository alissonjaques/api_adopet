package br.com.alura.adopet.api_adopet.controller;

import br.com.alura.adopet.api_adopet.domain.model.abrigo.AbrigoRepository;
import br.com.alura.adopet.api_adopet.domain.model.pet.*;
import br.com.alura.adopet.api_adopet.domain.model.pet.DadosCadastroPet;
import br.com.alura.adopet.api_adopet.infra.exception.TratadorDeErros;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@RequestMapping("pets")
@JsonIgnoreProperties
public class PetController {
    @Autowired
    private PetRepository repository;
    @Autowired
    AbrigoRepository abrigoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPet dados, UriComponentsBuilder uriBuilder) {
        var pet = new Pet(dados);
        repository.save(pet);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPet(pet));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPet>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"})
                                                            Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPet::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var pet = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPet(pet));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPet dados) {
        var pet = repository.getReferenceById(dados.id());
        pet.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPet(pet));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var pet = repository.getReferenceById(id);
        pet.excluir();
        return ResponseEntity.noContent().build();
    }
}
