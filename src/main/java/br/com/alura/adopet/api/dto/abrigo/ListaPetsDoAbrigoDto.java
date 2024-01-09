package br.com.alura.adopet.api.dto.abrigo;

import jakarta.validation.constraints.NotBlank;

public record ListaPetsDoAbrigoDto(@NotBlank String abrigo) {
}
