package br.com.alura.adopet.api_adopet.domain.model.adocao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> { // tipo objeto / tipo chave primária
    Page<Adocao> findAllByAtivoTrue(Pageable paginacao);
}
