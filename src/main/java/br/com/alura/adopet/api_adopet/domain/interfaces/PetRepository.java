package br.com.alura.adopet.api_adopet.domain.interfaces;

import br.com.alura.adopet.api_adopet.domain.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> { // tipo objeto / tipo chave primária
    Page<Pet> findAllByAtivoTrueAndAdotadoFalse(Pageable paginacao);
}