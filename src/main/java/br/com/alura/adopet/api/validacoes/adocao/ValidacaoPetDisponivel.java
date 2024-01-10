package br.com.alura.adopet.api.validacoes.adocao;

import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao {

    @Autowired
    PetRepository petRepository;

    public void validar(SolicitacaoAdocaoDto dto) {
        boolean pet = petRepository.existsByIdAndAdotadoFalse(dto.idPet());
        if (pet) {
            throw new ValidacaoException("Pet já foi adotado!");
        }

    }
}
