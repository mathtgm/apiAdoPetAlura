package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.tutor.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.tutor.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastraTutor(@RequestBody @Valid CadastroTutorDto cadastroTutorDto) {
        try {

            return ResponseEntity.ok().build();

        } catch (ValidacaoException erro) {

            return ResponseEntity.badRequest().body(erro.getMessage());

        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizacaoTutorDto atualizacaoTutorDto) {

        try {

            tutorService.atualizar(atualizacaoTutorDto);
            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException error) {

            return ResponseEntity.badRequest().body(error.getMessage());

        }

    }

}
