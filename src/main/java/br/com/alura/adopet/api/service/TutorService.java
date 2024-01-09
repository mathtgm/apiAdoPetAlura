package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.tutor.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.tutor.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.tutor.ValidacaoDadosTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    List<ValidacaoDadosTutor> validacaoDadosTutorList;

    public void cadastrar(CadastroTutorDto cadastroTutorDto) {

        boolean jaCadastrado = tutorRepository.existByTelefoneAndEmail(cadastroTutorDto.telefone(), cadastroTutorDto.email());

        if (jaCadastrado) {

            throw new ValidacaoException("Dados já cadastrado para outro tutor");

        }

        tutorRepository.save(new Tutor(cadastroTutorDto));

    }

    public void atualizar(AtualizacaoTutorDto atualizacaoTutorDto) {

        Tutor tutor = tutorRepository.getReferenceById(atualizacaoTutorDto.idTutor());

        tutor.atualizarDados(atualizacaoTutorDto);

    }

}
