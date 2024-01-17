package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.adocao.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.adocao.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.*;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.abrigo.ValidacaoCadastroAbrigo;
import br.com.alura.adopet.api.validacoes.abrigo.ValidacaoSeAbrigoExisteAbrigo;
import br.com.alura.adopet.api.validacoes.adocao.ValidacaoPetComAdocaoEmAndamento;
import br.com.alura.adopet.api.validacoes.adocao.ValidacaoPetDisponivel;
import br.com.alura.adopet.api.validacoes.adocao.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService adocaoService;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private Pet pet;

    @Mock
    private Tutor tutor;

    @Mock
    private Abrigo abrigo;

    @Mock
    LocalDateTime localDateTime;

    @Spy
    private Adocao adocao = new Adocao();

    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();

    @Mock
    private ValidacaoPetComAdocaoEmAndamento validacao1;

    @Mock
    private ValidacaoPetDisponivel validacao2;

    private SolicitacaoAdocaoDto dto;

    private AprovacaoAdocaoDto aprovacaoAdocaoDto;

    private ReprovacaoAdocaoDto reprovacaoAdocaoDto;

    @Captor
    ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    public void salvaSolicitacaoAdocao() {

        //ARRANGE
        this.dto = new SolicitacaoAdocaoDto(1l, 2l, "Qualquer motivo");
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);

        //ACT
        adocaoService.solicitar(dto);

        //ASSERT
        BDDMockito.then(adocaoRepository).should().save(adocaoCaptor.capture());
        Adocao adocao = adocaoCaptor.getValue();

        Assertions.assertEquals(pet, adocao.getPet());
        Assertions.assertEquals(tutor, adocao.getTutor());
        Assertions.assertEquals(dto.motivo(), adocao.getMotivo());

    }

    @Test
    void aprovaSolicitacaoDeAdocao() {
        this.aprovacaoAdocaoDto = new AprovacaoAdocaoDto(1l);
        BDDMockito.given(adocaoRepository.getReferenceById(aprovacaoAdocaoDto.idAdocao())).willReturn(adocao);
        BDDMockito.given(adocao.getTutor()).willReturn(tutor);
        BDDMockito.given(adocao.getPet()).willReturn(pet);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);
        BDDMockito.given(adocao.getData()).willReturn(localDateTime);

        adocaoService.aprovar(aprovacaoAdocaoDto);

        BDDMockito.then(adocao).should().alteraStatusParaAprovado();
        Assertions.assertEquals(pet, adocao.getPet());
        Assertions.assertEquals(tutor, adocao.getTutor());
        Assertions.assertEquals(adocao.getStatus(), StatusAdocao.APROVADO);
    }

    @Test
    void reprovaSolicitacaoDeAdocao() {
        this.reprovacaoAdocaoDto = new ReprovacaoAdocaoDto(1l, "Motivo Qualquer");
        BDDMockito.given(adocaoRepository.getReferenceById(reprovacaoAdocaoDto.idAdocao())).willReturn(adocao);
        BDDMockito.given(adocao.getTutor()).willReturn(tutor);
        BDDMockito.given(adocao.getPet()).willReturn(pet);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);
        BDDMockito.given(adocao.getData()).willReturn(localDateTime);

        adocaoService.reprovar(reprovacaoAdocaoDto);

        BDDMockito.then(adocao).should().alteraStatusParaReprovado(reprovacaoAdocaoDto.justificativa());
        Assertions.assertEquals(pet, adocao.getPet());
        Assertions.assertEquals(tutor, adocao.getTutor());
        Assertions.assertEquals(adocao.getStatus(), StatusAdocao.REPROVADO);
    }

    @Test
    public void verificarValidacaoDeSolicitadoAdocao() {

        //ARRANGE
        this.dto = new SolicitacaoAdocaoDto(1l, 2l, "Qualquer motivo");
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor);
        BDDMockito.given(pet.getAbrigo()).willReturn(abrigo);
        validacoes.add(validacao1);
        validacoes.add(validacao2);

        //ACT
        adocaoService.solicitar(dto);

        //ASSERT
        BDDMockito.then(validacao1).should().validar(dto);
        BDDMockito.then(validacao2).should().validar(dto);

    }
}