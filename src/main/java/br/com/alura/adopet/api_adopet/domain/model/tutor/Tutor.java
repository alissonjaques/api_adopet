package br.com.alura.adopet.api_adopet.domain.model.tutor;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "nome", "email"})
@NoArgsConstructor
@Entity
@Table(name = "tutores")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;

    public Tutor(DadosCadastroTutor dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public void atualizarInformacoes(DadosAtualizacaoTutor dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}