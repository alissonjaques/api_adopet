package br.com.alura.adopet.api_adopet.domain.model.usuario;

import br.com.alura.adopet.api_adopet.domain.model.endereco.Endereco;
import br.com.alura.adopet.api_adopet.domain.model.enums.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
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

    public Usuario(DadosCadastroUsuario dados) {
        this.ativo = true;
        this.perfil = dados.perfil();
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.telefone = dados.telefone();
        this.sobre = dados.sobre();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {
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
}