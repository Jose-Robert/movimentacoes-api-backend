package br.com.itau.challenge.conta.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaTO implements Serializable {

    @JsonProperty(value = "agencia")
    private String agencia;

    @JsonProperty(value = "numero_conta")
    @SerializedName(value = "numero_conta")
    private String numeroConta;

    @SerializedName(value = "digito_conta")
    @JsonProperty(value = "digito_conta")
    private String digito;

    @JsonProperty(value = "valor")
    private BigDecimal valor;
}
