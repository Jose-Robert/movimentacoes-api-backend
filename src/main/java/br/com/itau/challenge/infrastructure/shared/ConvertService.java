package br.com.itau.challenge.infrastructure.shared;

import br.com.itau.challenge.historico.model.dto.HistoricoTO;
import br.com.itau.challenge.historico.model.entity.Historico;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConvertService {

    private final ModelMapper mapper;

    public Historico dtoToEntity(HistoricoTO historicoTO) {
        return mapper.map(historicoTO, Historico.class);
    }

    public HistoricoTO entityToDto(Historico historico) {
        return mapper.map(historico, HistoricoTO.class);
    }
}
