package br.com.alura.adopet.api.validacoes.tutor;

import br.com.alura.adopet.api.dto.tutor.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoEmailJaCadastrado implements ValidacaoDadosTutor {

    @Autowired
    TutorRepository tutorRepository;

    @Override
    public void valida(CadastroTutorDto cadastroTutorDto) {
        if (tutorRepository.existsByEmail(cadastroTutorDto.email())) {
            throw new ValidacaoException("Email " + cadastroTutorDto.email() + " já cadastrado");
        }
    }
}
