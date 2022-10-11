package br.com.itau.challenge.conta.controller;

import br.com.itau.challenge.conta.model.dto.SaldoTO;
import br.com.itau.challenge.conta.service.ContaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping(path = "/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    @SqsListener(value = "${cloud.aws.end-point.uri}" ,deletionPolicy = SqsMessageDeletionPolicy.NO_REDRIVE)
    public void receiveMessageSqs(String message) {
        log.info("Mensagem Recebida Usando SQS Listener " + message);
        contaService.consumer(message);
    }

    @GetMapping(value = "/{idConta}/saldos")
    public ResponseEntity<SaldoTO> consultaSaldo(@PathVariable("idConta") String idConta) {
        return ResponseEntity.ok(contaService.consultaSaldo(idConta));
    }
}
