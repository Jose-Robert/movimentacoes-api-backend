package br.com.itau.challenge.historico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoricoTO implements Serializable {

    private Long id;
    private String descricao;
    private LocalDateTime dataCriacao;
}
