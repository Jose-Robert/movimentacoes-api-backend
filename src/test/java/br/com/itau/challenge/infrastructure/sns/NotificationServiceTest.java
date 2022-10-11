package br.com.itau.challenge.infrastructure.sns;

import br.com.itau.challenge.configuration.SNSConfig;
import br.com.itau.challenge.historico.service.HistoricoService;
import br.com.itau.challenge.utils.Constantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class NotificationServiceTest {

    @Mock
    private SNSConfig snsConfig;

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private NotificationService notificationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(notificationService, "topicArn", "Teste");
        historicoService = Mockito.spy(historicoService);
        notificationService = Mockito.spy(notificationService);
    }

    @Test
    void test_notification() {
        String message = Constantes.MESSAGE_SALDO_NEGATIVO + "-200";

        doNothing().when(historicoService).gravarHistorico(message);
        Mockito.doAnswer(invocationOnMock -> null).when(notificationService).notification("-150");
    }
}