package br.com.alura.adopet.api_adopet.domain.model;

import br.com.alura.adopet.api_adopet.domain.enums.Perfil;
import br.com.alura.adopet.api_adopet.application.DTOs.usuario.UpdateUsuarioDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.usuario.CreateUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String sobre;
    @Embedded
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuario(CreateUsuarioDTO dados) {
        this.ativo = true;
        this.perfil = dados.perfil();
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.telefone = dados.telefone();
        this.sobre = dados.sobre();
        this.endereco = new Endereco(dados.endereco());
    }

    public Usuario(Long id, String nome, String email){
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public void atualizarInformacoes(UpdateUsuarioDTO dados) {
        if (dados.perfil() != null) {
            this.perfil = dados.perfil();
        }
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.sobre() != null) {
            this.sobre = dados.sobre();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}