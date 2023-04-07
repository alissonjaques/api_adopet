package br.com.alura.adopet.api_adopet.domain.model.abrigo;

import br.com.alura.adopet.api_adopet.domain.model.endereco.Endereco;

public record DadosDetalhamentoAbrigo(Long id, String nome, String email, String telefone, String sobre, Endereco endereco) {
    public DadosDetalhamentoAbrigo(Abrigo abrigo){
        this(abrigo.getId(),abrigo.getNome(),abrigo.getEmail(),abrigo.getTelefone(),abrigo.getSobre(),abrigo.getEndereco());
    }
}