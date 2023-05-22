package br.com.alura.adopet.api_adopet.domain.model;

import br.com.alura.adopet.api_adopet.application.DTOs.adocao.CreateAdocaoDTO;
import br.com.alura.adopet.api_adopet.application.DTOs.adocao.UpdateAdocaoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "adocoes")
@Entity(name = "adocao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Adocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    @OneToOne
    private Pet pet;
    @ManyToOne
    private Usuario tutor;
    private LocalDateTime data;

    public Adocao(CreateAdocaoDTO dados) {
        this.ativo = true;
        this.data = LocalDateTime.now();
        this.pet = dados.pet();
        this.tutor = dados.tutor();
    }

    public void atualizarInformacoes(UpdateAdocaoDTO dados) {
        if (dados.pet() != null) {
            this.pet = dados.pet();
        }
        if (dados.tutor() != null) {
            this.tutor = dados.tutor();
        }
        this.data = LocalDateTime.now();
    }

    public void excluir() {
        this.ativo = false;
        getPet().cancelarAdocao();
    }
}
