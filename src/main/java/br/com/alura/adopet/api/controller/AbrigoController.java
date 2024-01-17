package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.abrigo.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.abrigo.CadastroPetAbrigoDto;
import br.com.alura.adopet.api.dto.abrigo.ListaPetsDoAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<Abrigo>> listar() {
        return ResponseEntity.ok().body(abrigoService.listaAbrigos());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastraAbrigoDto abrigoDto) {

        try {
            abrigoService.cadastrarAbrigo(abrigoDto);
        } catch (ValidacaoException erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }

        return ResponseEntity.ok().body("Abrigo cadastrado com sucesso!");

    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<Pet>> listarPets(@PathVariable ListaPetsDoAbrigoDto idOuNome) {

        try {

            return ResponseEntity.ok().body(abrigoService.listarPets(idOuNome));

        } catch (EntityNotFoundException erro) {

            return ResponseEntity.notFound().build();

        }

    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastroPetAbrigoDto cadastroPetAbrigoDto) {

        try {

            abrigoService.cadastrarPetsAbrigo(idOuNome, cadastroPetAbrigoDto);
            return ResponseEntity.ok().body("Pet cadastrado no abrigo com sucesso!");

        } catch (EntityNotFoundException erro) {

            return ResponseEntity.notFound().build();

        }

    }

}
