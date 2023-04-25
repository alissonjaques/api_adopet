package br.com.alura.adopet.api_adopet.controller;

import br.com.alura.adopet.api_adopet.application.DTOs.pet.GetAllPet;
import br.com.alura.adopet.api_adopet.application.DTOs.pet.GetPet;
import br.com.alura.adopet.api_adopet.application.DTOs.pet.UpdatePet;
import br.com.alura.adopet.api_adopet.domain.enums.Perfil;
import br.com.alura.adopet.api_adopet.domain.interfaces.PetRepository;
import br.com.alura.adopet.api_adopet.domain.model.Pet;
import br.com.alura.adopet.api_adopet.application.DTOs.pet.CreatePet;
import br.com.alura.adopet.api_adopet.domain.interfaces.UsuarioRepository;
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
public class PetController {
    @Autowired
    private PetRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CreatePet dados, UriComponentsBuilder uriBuilder) {
        var abrigo = usuarioRepository.getReferenceById(dados.usuario().getId());
        if(!abrigo.getAtivo()){
            return ResponseEntity.badRequest().body("Não foi possível cadastrar o pet," +
                    " abrigo não encontrado.");
        }
        if(abrigo.getPerfil().equals(Perfil.TUTOR)){
            return ResponseEntity.badRequest().body("Não foi possível cadastrar o pet," +
                    " pois o pet deve pertencer a um abrigo e não a um tutor.");
        }
        var pet = new Pet(dados);
        repository.save(pet);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new GetPet(pet));
    }

    @GetMapping
    public ResponseEntity<Page<GetAllPet>> listar(@PageableDefault(size = 9, page = 0, sort = {"nome"})
                                                            Pageable paginacao){
        var page = repository.findAllByAtivoTrueAndAdotadoFalse(paginacao).map(GetAllPet::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var pet = repository.getReferenceById(id);
        if(pet.getAdotado()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GetPet(pet));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UpdatePet dados) {
        var pet = repository.getReferenceById(dados.id());
        pet.atualizarInformacoes(dados);
        return ResponseEntity.ok(new GetPet(pet));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var pet = repository.getReferenceById(id);
        pet.excluir();
        return ResponseEntity.noContent().build();
    }
}
