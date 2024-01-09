package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.tutor.AtualizaDadosTutor;
import br.com.alura.adopet.api.dto.tutor.CadastroTutorDto;
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

    public void cadastraTutor(CadastroTutorDto cadastroTutorDto) {

        validacaoDadosTutorList.forEach(v -> v.valida(cadastroTutorDto));

        Tutor tutor = new Tutor(cadastroTutorDto);

        tutorRepository.save(tutor);

    }

    public void atualizaDadosTutor(AtualizaDadosTutor atualizaDadosTutor) {

        Tutor tutor = tutorRepository.getReferenceById(atualizaDadosTutor.idTutor());

        tutor.atualizaDadosTutor(atualizaDadosTutor);

    }

}
