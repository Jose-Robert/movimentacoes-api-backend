package br.com.itau.challenge.conta.repository;

import br.com.itau.challenge.conta.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByAgenciaAndNumeroContaAndDigitoConta(String agencia, String numeroConta, String digito);
}
