package br.com.alura.adopet.api.dto.abrigo;

public record CadastroAbrigoDto(
        Long idAbrigo,
        String nome,
        String telefone,
        String email) {
}
