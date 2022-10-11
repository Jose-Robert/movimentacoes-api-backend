package br.com.itau.challenge.historico.controller;

import br.com.itau.challenge.historico.model.dto.HistoricoTO;
import br.com.itau.challenge.historico.model.entity.Historico;
import br.com.itau.challenge.historico.service.HistoricoService;
import br.com.itau.challenge.infrastructure.shared.ConvertService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Collections;

@AutoConfigureMockMvc
@SpringBootTest
class HistoricoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HistoricoService service;

    @Autowired
    private ConvertService convertService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        service = Mockito.spy(service);
        convertService = Mockito.spy(convertService);
    }

    @Test
    void test_consultarHistoricos() throws Exception {
        Historico historico = Historico.builder()
                .id(1L)
                .descricao("Teste")
                .dataCriacao(LocalDateTime.now())
                .build();

        Mockito.when(service.listarHistoricos()).thenReturn(Collections.singletonList(historico));
        Mockito.when(convertService.entityToDto(historico)).thenReturn(HistoricoTO.builder()
                        .descricao("Teste")
                        .dataCriacao(LocalDateTime.now())
                .build());

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/historicos")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }
}