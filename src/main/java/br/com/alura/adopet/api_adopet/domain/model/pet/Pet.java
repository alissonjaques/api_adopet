package br.com.alura.adopet.api_adopet.domain.model.pet;

import br.com.alura.adopet.api_adopet.domain.model.abrigo.Abrigo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pets")
@Entity(name = "Pet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    @ManyToOne
    private Abrigo abrigo;
    private String nome;
    private String descricao;
    private Boolean adotado;
    private Integer idade;
    private String imagem;

    public Pet(DadosCadastroPet dados) {
        this.ativo = true;
        this.abrigo = dados.abrigo();
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.adotado = false;
        this.idade = dados.idade();
        this.imagem = dados.imagem();
    }

    public void atualizarInformacoes(DadosAtualizacaoPet dados) {
        if (dados.abrigo() != null) {
            this.abrigo = dados.abrigo();
        }
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.idade() != null) {
            this.idade = dados.idade();
        }
        if (dados.imagem() != null) {
            this.imagem = dados.imagem();
        }
    }

    public void excluir() {
        this.ativo = false;
    }

    public void adotar() {
        this.adotado = true;
    }
}
