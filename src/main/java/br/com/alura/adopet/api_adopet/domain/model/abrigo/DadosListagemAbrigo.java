package br.com.alura.adopet.api_adopet.domain.model.abrigo;

public record DadosListagemAbrigo(Long id, String nome, String email, String sobre) {
    public DadosListagemAbrigo(Abrigo abrigo){
        this(abrigo.getId(), abrigo.getNome(), abrigo.getEmail(), abrigo.getSobre());
    }
}
