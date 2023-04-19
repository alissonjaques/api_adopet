package br.com.alura.adopet.api_adopet.domain.model.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { // tipo objeto / tipo chave primária
    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
    UserDetails findByEmail(String email);
}