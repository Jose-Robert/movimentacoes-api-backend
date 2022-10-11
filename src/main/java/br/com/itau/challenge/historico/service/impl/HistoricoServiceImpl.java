package br.com.itau.challenge.historico.service.impl;

import br.com.itau.challenge.historico.model.entity.Historico;
import br.com.itau.challenge.historico.repository.HistoricoRepository;
import br.com.itau.challenge.historico.service.HistoricoService;
import br.com.itau.challenge.infrastructure.shared.ConvertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoricoServiceImpl implements HistoricoService {

    private final HistoricoRepository historicoRepository;

    @Override
    public void gravarHistorico(String descricao) {
        Historico historico = Historico.builder()
                .descricao(descricao)
                .dataCriacao(LocalDateTime.now())
                .build();

        historicoRepository.saveAndFlush(historico);
    }

    @Override
    public List<Historico> listarHistoricos() {
        return historicoRepository.findAll();
    }
}
