package br.com.itau.challenge.conta.service.impl;

import br.com.itau.challenge.conta.model.dto.ContaTO;
import br.com.itau.challenge.conta.model.dto.SaldoTO;
import br.com.itau.challenge.conta.model.entity.Conta;
import br.com.itau.challenge.conta.repository.ContaRepository;
import br.com.itau.challenge.conta.service.ContaService;
import br.com.itau.challenge.infrastructure.exception.ObjectNotFoundException;
import br.com.itau.challenge.infrastructure.sns.NotificationService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {

    private final ContaRepository contaRepository;

    private final NotificationService notificationService;

    public void consumer(String message) {
        this.findAccount(message);
    }

    @Override
    public SaldoTO consultaSaldo(String idConta) {

        String agencia = idConta.substring(0,4);
        String numero = idConta.substring(4,10);
        String dac = idConta.substring(10,11);

        Conta conta = contaRepository.findByAgenciaAndNumeroContaAndDigitoConta(agencia,
                numero, dac).orElseThrow(
                ObjectNotFoundException::new);

        return SaldoTO.builder()
                .saldo(conta.getSaldo())
                .build();
    }

    private void findAccount(String message) {
        Gson gson = new Gson();
        ContaTO contaVO = gson.fromJson(message, ContaTO.class);
        Conta conta = contaRepository.findByAgenciaAndNumeroContaAndDigitoConta(contaVO.getAgencia(),
                contaVO.getNumeroConta(), contaVO.getDigito()).orElseThrow(ObjectNotFoundException::new);

        BigDecimal novoSaldo = this.sacar(contaVO, conta);

        if (novoSaldo.compareTo(BigDecimal.valueOf(-1)) <= 0) {
            notificationService.notification(String.valueOf(novoSaldo));
        }
    }

    private BigDecimal sacar(ContaTO dto, Conta conta) {
        BigDecimal novoSaldo = new BigDecimal(String.valueOf(conta.getSaldo())).subtract(dto.getValor());
        conta.setSaldo(novoSaldo);
        contaRepository.save(conta);
        return novoSaldo;
    }

}
