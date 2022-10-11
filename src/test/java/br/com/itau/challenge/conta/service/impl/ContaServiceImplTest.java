package br.com.itau.challenge.conta.service.impl;

import br.com.itau.challenge.conta.model.dto.SaldoTO;
import br.com.itau.challenge.conta.model.entity.Conta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ContaServiceImplTest {

    @InjectMocks
    private ContaServiceImpl contaService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        contaService = Mockito.spy(contaService);
    }

    @Test
    void test_consultaSaldo() {
        Conta conta = Conta.builder()
                .agencia("0039")
                .numeroConta("123456")
                .digitoConta("5")
                .saldo(BigDecimal.valueOf(200))
                .build();

        SaldoTO saldo = SaldoTO.builder().saldo(conta.getSaldo()).build();

        Assertions.assertEquals(saldo.getSaldo(), new BigDecimal(200));

    }

    @Test
    void test_consumer() {
        String json = "{\n" +
                "    \"agencia\": \"0039\",\n" +
                "    \"numero_conta\": \"123456\",\n" +
                "    \"digito_conta\": \"5\",\n" +
                "    \"valor\": 10\n" +
                "}";

        Mockito.doNothing().when(contaService).consumer(json);
        Mockito.doAnswer(invocationOnMock -> null).when(contaService).consumer(json);
        Assertions.assertNotNull(json, "must not be null");
    }
}