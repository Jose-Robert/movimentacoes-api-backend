package br.com.itau.challenge.conta.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_conta")
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agencia")
    private String agencia;

    @Column(name = "numero_conta")
    private String numeroConta;

    @Column(name = "digito_conta")
    private String digitoConta;

    @Column(name = "saldo")
    private BigDecimal saldo;
}
