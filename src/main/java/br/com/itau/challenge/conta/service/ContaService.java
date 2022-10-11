package br.com.itau.challenge.conta.service;

import br.com.itau.challenge.conta.model.dto.SaldoTO;

import java.math.BigDecimal;

public interface ContaService {

    void consumer(String message);
    SaldoTO consultaSaldo(String idConta);
}
