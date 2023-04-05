package br.com.alura.adopet.api_adopet.domain.model.tutor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Page<Tutor> findAllByAtivoTrue(Pageable paginacao);
}