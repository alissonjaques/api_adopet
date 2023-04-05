package br.com.alura.adopet.api_adopet.controller;

import br.com.alura.adopet.api_adopet.domain.model.tutor.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {
    @Autowired
    private TutorRepository repository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTutor dados) {
        if (!dados.senha().equals(dados.confirmacaoSenha())) {
            return ResponseEntity.badRequest().body("A senha e sua confirmação não conferem!");
        }
        var tutor = new Tutor(dados);
        repository.save(tutor);
        return ResponseEntity.ok(new DadosTutor(tutor));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTutor>> listar(@PageableDefault(size = 10, page = 0, sort = {"nome"})
                                                               Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTutor::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var tutor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTutor(tutor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTutor dados) {
        if (dados.senha() != null && !dados.senha().equals(dados.confirmacaoSenha())) {
            return ResponseEntity.badRequest().body("A senha e sua confirmação não conferem!");
        }
        var tutor = repository.getReferenceById(dados.id());
        tutor.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTutor(tutor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var tutor = repository.getReferenceById(id);
        tutor.excluir();
        return ResponseEntity.noContent().build();
    }
}

