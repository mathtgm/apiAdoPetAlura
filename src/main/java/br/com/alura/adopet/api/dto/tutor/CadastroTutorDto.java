package br.com.alura.adopet.api.dto.tutor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroTutorDto(@NotBlank String nome,
                               @NotBlank
                               @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
                               String telefone,
                               @NotBlank String email) {
}
