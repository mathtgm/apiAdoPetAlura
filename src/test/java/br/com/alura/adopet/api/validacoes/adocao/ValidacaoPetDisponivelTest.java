package br.com.alura.adopet.api.validacoes.adocao;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {

    @Mock
    PetRepository petRepository;

    @Mock
    Pet pet;

    @Mock
    SolicitacaoAdocaoDto dto;

    @InjectMocks
    ValidacaoPetDisponivel validacaoPetDisponivel;

    @Test
    @DisplayName("Permite solicitacao para pet nao adotado")
    public void permiteSolicitacaoAdocaoPetNaoAdotado() {

        BDDMockito.given(petRepository.existsByIdAndAdotadoTrue(pet.getId())).willReturn(false);

        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto));


    }

    @Test
    @DisplayName("Nao permite solicitacao, pois o pet ja foi adotado")
    public void naoPermiteSolicitacaoPetAdotado() {

        BDDMockito.given(petRepository.existsByIdAndAdotadoTrue(pet.getId())).willReturn(true);

        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetDisponivel.validar(dto));

    }
}