package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.abrigo.CadastroPetAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.TipoPet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacoes.abrigo.ValidacaoCadastroAbrigo;
import br.com.alura.adopet.api.validacoes.abrigo.ValidacaoSeAbrigoExisteAbrigo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    AbrigoService abrigoService;

    @Mock
    AbrigoRepository abrigoRepository;

    CadastraAbrigoDto cadastraAbrigoDto;

    CadastroPetAbrigoDto cadastroPetAbrigoDto;

    @Captor
    ArgumentCaptor<Abrigo> abrigoCaptor;

    @Mock
    Abrigo abrigo;

    @Spy
    List<ValidacaoCadastroAbrigo> validacaoCadastroAbrigo = new ArrayList<>();

    @Mock
    ValidacaoSeAbrigoExisteAbrigo validacaoSeAbrigoExisteAbrigo;

    @Test
    void verificarCadastroDeAbrigo() {
        this.cadastraAbrigoDto = new CadastraAbrigoDto("Nome", "19312344422", "email@email.com");
        validacaoCadastroAbrigo.add(validacaoSeAbrigoExisteAbrigo);

        abrigoService.cadastrarAbrigo(cadastraAbrigoDto);

        BDDMockito.then(abrigoRepository).should().save(abrigoCaptor.capture());
        Abrigo abrigoCaptorValue = abrigoCaptor.getValue();

        Assertions.assertEquals(cadastraAbrigoDto.nome(), abrigoCaptorValue.getNome());
        Assertions.assertEquals(cadastraAbrigoDto.email(), abrigoCaptorValue.getEmail());
        Assertions.assertEquals(cadastraAbrigoDto.telefone(), abrigoCaptorValue.getTelefone());
        BDDMockito.then(validacaoSeAbrigoExisteAbrigo).should().validar(cadastraAbrigoDto);

    }

}