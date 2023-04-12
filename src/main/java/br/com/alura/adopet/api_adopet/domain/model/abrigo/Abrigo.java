package br.com.alura.adopet.api_adopet.domain.model.abrigo;

import br.com.alura.adopet.api_adopet.domain.model.endereco.Endereco;
import br.com.alura.adopet.api_adopet.domain.model.enums.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "abrigos")
@Entity(name = "Abrigo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Abrigo {
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

    public Abrigo(DadosCadastroAbrigo dados) {
        this.ativo = true;
        this.perfil = Perfil.ABRIGO;
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.telefone = dados.telefone();
        this.sobre = dados.sobre();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoAbrigo dados) {
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