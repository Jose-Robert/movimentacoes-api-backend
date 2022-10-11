package br.com.itau.challenge.historico.service.impl;

import br.com.itau.challenge.historico.model.entity.Historico;
import br.com.itau.challenge.historico.repository.HistoricoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class HistoricoServiceImplTest {

    @Mock
    private HistoricoRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_gravarHistorico() {
        Historico historico = Historico.builder()
                .descricao("Teste")
                .dataCriacao(LocalDateTime.now())
                .build();

        Mockito.when(repository.saveAndFlush(Mockito.any())).thenReturn(historico);
        Assertions.assertNotNull(historico, "must not be null");
    }

    @Test
    void test_listarHistoricos() {
        Historico historico = Historico.builder()
                .id(1L)
                .descricao("Teste")
                .dataCriacao(LocalDateTime.now())
                .build();

        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(historico));
        Assertions.assertNotNull(historico, "must not be null");
    }
}