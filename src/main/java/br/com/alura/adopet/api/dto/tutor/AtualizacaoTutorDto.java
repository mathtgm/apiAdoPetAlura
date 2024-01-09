package br.com.alura.adopet.api.dto.tutor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoTutorDto(@NotNull Long idTutor,
                                  @NotBlank String email,
                                  @NotBlank String nome,
                                  @NotBlank String telefone) {
}
