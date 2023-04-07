package br.com.alura.adopet.api_adopet.domain.model.abrigo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> { // tipo objeto / tipo chave primária
    Page<Abrigo> findAllByAtivoTrue(Pageable paginacao);
}