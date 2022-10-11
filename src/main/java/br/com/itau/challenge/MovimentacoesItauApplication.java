package br.com.itau.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;

@SpringBootApplication(exclude = { ContextStackAutoConfiguration.class, ContextInstanceDataAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class })
public class MovimentacoesItauApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovimentacoesItauApplication.class, args);
	}

}
