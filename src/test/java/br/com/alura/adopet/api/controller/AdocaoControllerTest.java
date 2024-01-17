package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdocaoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AdocaoService adocaoService;

    @Test
    void devolveCod400ParaUmaSolicitacaoDeAdocao() throws Exception {

        String json = "{}";

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());

    }

    @Test
    void devolveCod200ParaUmaSolicitacaoDeAdocao() throws Exception {

        String json = """
                    {
                        "idPet": 1,
                        "idTutor": 1,
                        "motivo": "MOtivo qualquer"
                    }
                """;

        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/adocoes")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());

    }

}