package br.com.alura.adopet.api.dto.abrigo;

import jakarta.validation.constraints.NotBlank;

public record CadastraAbrigoDto(@NotBlank String nome, @NotBlank String telefone, @NotBlank String email) {
}
