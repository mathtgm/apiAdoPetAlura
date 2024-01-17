package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.CadastroPetAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Probabilidade alta para cachorro jovem com pouco peso")
    public void cenario01() {
        // Pet com 4 anos e 5Kg - Probabilidade ALTA
        Abrigo abrigo = new Abrigo("Amigo de rua", "19 9999785", "maria@email.com");
        CadastroPetAbrigoDto cadastroPetAbrigoDto = new CadastroPetAbrigoDto(TipoPet.CACHORRO, "Babi", "Pincher", 4, "PRETA", 5f);

        Pet pet = new Pet(abrigo, cadastroPetAbrigoDto);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);

    }

    @Test
    @DisplayName("Probabilidade media para gato velho com pouco peso")
    public void cenario02() {
        // Pet com 15 anos e 5Kg - Probabilidade MEDIA
        Abrigo abrigo = new Abrigo("Amigo de rua", "19 9999785", "maria@email.com");
        CadastroPetAbrigoDto cadastroPetAbrigoDto = new CadastroPetAbrigoDto(TipoPet.GATO, "Babi", "Vira-lata", 20, "PRETA", 5f);

        Pet pet = new Pet(abrigo, cadastroPetAbrigoDto);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);

    }

}