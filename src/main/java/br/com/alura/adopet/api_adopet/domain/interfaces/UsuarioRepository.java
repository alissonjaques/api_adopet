package br.com.alura.adopet.api_adopet.domain.interfaces;

import br.com.alura.adopet.api_adopet.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { // tipo objeto / tipo chave primária
    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
    UserDetails findByEmail(String email);
    @Query("""
            select u.email from Usuario u
            where u.email = :email
           """)
    String findEmail(String email);
}