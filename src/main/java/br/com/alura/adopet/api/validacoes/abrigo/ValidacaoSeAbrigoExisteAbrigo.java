package br.com.alura.adopet.api.validacoes.abrigo;

import br.com.alura.adopet.api.dto.abrigo.CadastraAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoSeAbrigoExisteAbrigo implements ValidacaoCadastroAbrigo {

    @Autowired
    AbrigoRepository abrigoRepository;

    public void verifica(CadastraAbrigoDto abrigoDto) {
        boolean nomeJaCadastrado = this.abrigoRepository.existsByNome(abrigoDto.nome());
        boolean telefoneJaCadastrado = this.abrigoRepository.existsByTelefone(abrigoDto.telefone());
        boolean emailJaCadastrado = this.abrigoRepository.existsByEmail(abrigoDto.email());

        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
    }
}
