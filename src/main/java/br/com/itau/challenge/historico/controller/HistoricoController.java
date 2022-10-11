package br.com.itau.challenge.historico.controller;

import br.com.itau.challenge.historico.model.dto.HistoricoTO;
import br.com.itau.challenge.historico.model.entity.Historico;
import br.com.itau.challenge.historico.service.HistoricoService;
import br.com.itau.challenge.infrastructure.shared.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/historicos")
@RequiredArgsConstructor
public class HistoricoController {

    private final HistoricoService historicoService;
    private final ConvertService convertService;

    @GetMapping
    public ResponseEntity<List<HistoricoTO>> consultarHistoricos() {
        List<Historico> historicos = historicoService.listarHistoricos();
        return ResponseEntity.ok().body(historicos.stream().map(convertService::entityToDto).collect(Collectors.toList()));
    }

}
