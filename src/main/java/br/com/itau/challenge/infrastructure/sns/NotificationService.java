package br.com.itau.challenge.infrastructure.sns;

import br.com.itau.challenge.configuration.SNSConfig;
import br.com.itau.challenge.historico.service.HistoricoService;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static br.com.itau.challenge.utils.Constantes.MESSAGE_SALDO_NEGATIVO;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationService {

    @Value("${cloud.aws.topic-arn}")
    private String topicArn;
    private final SNSConfig snsConfig;

    private final HistoricoService historicoService;

    public void notification(String value) {
        String message = MESSAGE_SALDO_NEGATIVO + value;
        PublishRequest request = new PublishRequest()
                .withMessage(message)
                .withTopicArn(topicArn);
        snsConfig.getClient().publish(request);
        log.info("Notificação por SMS enviada com sucesso.");

        historicoService.gravarHistorico(message);
    }
}
