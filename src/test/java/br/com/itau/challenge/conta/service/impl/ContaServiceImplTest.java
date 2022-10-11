package br.com.itau.challenge.conta.service.impl;

import br.com.itau.challenge.conta.model.dto.SaldoTO;
import br.com.itau.challenge.conta.model.entity.Conta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class ContaServiceImplTest {

    @InjectMocks
    private ContaServiceImpl contaService;

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
}