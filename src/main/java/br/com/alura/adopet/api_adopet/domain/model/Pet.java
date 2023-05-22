package br.com.alura.adopet.api_adopet.domain.model;

import br.com.alura.adopet.api_adopet.application.DTOs.pet.UpdatePetDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.pet.CreatePetDTO;
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
    private Usuario usuario;
    private String nome;
    private String descricao;
    private Boolean adotado;
    private Integer idade;
    private String imagem;

    public Pet(CreatePetDTO dados) {
        this.ativo = true;
        this.usuario = dados.usuario();
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.adotado = false;
        this.idade = dados.idade();
        this.imagem = dados.imagem();
    }

    public void atualizarInformacoes(UpdatePetDTO dados) {
        if (dados.usuario() != null) {
            this.usuario = dados.usuario();
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
        this.ativo = false;
    }

    public void cancelarAdocao() {
        this.adotado = false;
        this.ativo = true;
    }
}
