package br.com.alura.adopet.api_adopet.domain.model.adocao;

import br.com.alura.adopet.api_adopet.domain.model.usuario.Usuario;
import br.com.alura.adopet.api_adopet.domain.model.pet.Pet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "adocoes")
@Entity(name = "Adocao")
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

    public Adocao(DadosCadastroAdocao dados) {
        this.ativo = true;
        this.data = LocalDateTime.now();
        this.pet = dados.pet();
        this.tutor = dados.tutor();
    }

    public void atualizarInformacoes(DadosAtualizacaoAdocao dados) {
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
