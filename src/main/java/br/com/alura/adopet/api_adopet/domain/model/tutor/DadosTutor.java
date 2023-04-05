package br.com.alura.adopet.api_adopet.domain.model.tutor;

public record DadosTutor(Long id, String nome) {
    public DadosTutor(Tutor tutor) {
        this(tutor.getId(), tutor.getNome());
    }
}