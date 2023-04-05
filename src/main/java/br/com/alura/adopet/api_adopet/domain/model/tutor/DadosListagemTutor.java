package br.com.alura.adopet.api_adopet.domain.model.tutor;

public record DadosListagemTutor(Long id, String nome, String email) {
    public DadosListagemTutor(Tutor tutor){
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}
