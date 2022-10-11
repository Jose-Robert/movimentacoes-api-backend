package br.com.itau.challenge.historico.service;

import br.com.itau.challenge.historico.model.entity.Historico;

import java.util.List;

public interface HistoricoService {

    void gravarHistorico(String descricao);

    List<Historico> listarHistoricos();
}
