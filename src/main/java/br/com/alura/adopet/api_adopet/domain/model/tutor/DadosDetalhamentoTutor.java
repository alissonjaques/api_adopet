package br.com.alura.adopet.api_adopet.domain.model.tutor;

public record DadosDetalhamentoTutor(Long id, String nome, String email) {
    public DadosDetalhamentoTutor(Tutor tutor){
        this(tutor.getId(),tutor.getNome(),tutor.getEmail());
    }
}