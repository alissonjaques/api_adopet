package br.com.alura.adopet.api_adopet.controller;

import br.com.alura.adopet.api_adopet.application.DTOs.pet.GetAllPetDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.pet.GetPetDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.pet.UpdatePetDTO;
import br.com.alura.adopet.api_adopet.domain.interfaces.PetRepository;
import br.com.alura.adopet.api_adopet.application.DTOs.pet.CreatePetDTO;
import br.com.alura.adopet.api_adopet.domain.services.PetService;
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
@RequestMapping("pets")
@SecurityRequirement(name = "bearer-key")
public class PetController {
    @Autowired
    private PetRepository repository;
    @Autowired
    PetService petService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CreatePetDTO dados, UriComponentsBuilder uriBuilder) {
        var pet = petService.cadastrar(dados);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new GetPetDTO(pet));
    }

    @GetMapping
    public ResponseEntity<Page<GetAllPetDTO>> listar(@PageableDefault(size = 9, page = 0, sort = {"nome"})
                                                            Pageable paginacao){
        var page = repository.findAllByAtivoTrueAndAdotadoFalse(paginacao).map(GetAllPetDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var pet = repository.getReferenceById(id);
        if(pet.getAdotado() || !pet.getAtivo()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GetPetDTO(pet));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid UpdatePetDTO dados) {
        var pet = petService.atualizar(dados);
        return ResponseEntity.ok(new GetPetDTO(pet));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var pet = repository.getReferenceById(id);
        pet.excluir();
        return ResponseEntity.noContent().build();
    }
}
