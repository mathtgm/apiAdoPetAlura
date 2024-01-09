package br.com.alura.adopet.api.validacoes.tutor;

import br.com.alura.adopet.api.dto.tutor.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTelefoneJaCadastrado implements ValidacaoDadosTutor {

    @Autowired
    TutorRepository tutorRepository;

    @Override
    public void valida(CadastroTutorDto cadastroTutor) {
        if (tutorRepository.existsByTelefone(cadastroTutor.telefone())) {
            throw new ValidacaoException("Telefone já cadastrado");
        }
    }

}
