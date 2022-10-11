package br.com.itau.challenge.conta.controller;

import br.com.itau.challenge.conta.model.dto.SaldoTO;
import br.com.itau.challenge.conta.service.ContaService;
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

import java.math.BigDecimal;

@AutoConfigureMockMvc
@SpringBootTest
class ContaControllerTest {

    @Autowired
    private ContaService contaService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        contaService = Mockito.spy(contaService);
    }

    @Test
    void test_consultaSaldoReturn200_Ok() throws Exception {
        String idconta = "00391234565";
        Mockito.when(contaService.consultaSaldo(idconta)).thenReturn(SaldoTO.builder().saldo(new BigDecimal(200)).build());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/contas/" + idconta + "/saldos")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }
}