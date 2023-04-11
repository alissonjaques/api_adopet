package br.com.alura.adopet.api_adopet.controller;

import br.com.alura.adopet.api_adopet.domain.model.adocao.*;
import br.com.alura.adopet.api_adopet.domain.model.pet.PetRepository;
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
public class AdocaoController {
    @Autowired
    private AdocaoRepository repository;
    @Autowired
    private PetRepository petRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAdocao dados, UriComponentsBuilder uriBuilder) {
        var pet = petRepository.getReferenceById(dados.pet().getId());
        if(pet.getAdotado()){
            return ResponseEntity.badRequest().body("Não foi possível realizar a adoção," +
                    " pois o pet informado já foi adotado.");
        }
        pet.adotar();
        var adocao = new Adocao(dados);
        repository.save(adocao);
        var uri = uriBuilder.path("/adocoes/{id}").buildAndExpand(adocao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAdocao(adocao));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAdocao>> listar(@PageableDefault(size = 10, page = 0, sort = {"pet"})
                                                         Pageable paginacao){
        System.out.println("aqui");
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAdocao::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var adocao = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAdocao(adocao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAdocao dados) {
        var adocao = repository.getReferenceById(dados.id());
        adocao.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoAdocao(adocao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var adocao = repository.getReferenceById(id);
        adocao.excluir();
        return ResponseEntity.noContent().build();
    }
}
