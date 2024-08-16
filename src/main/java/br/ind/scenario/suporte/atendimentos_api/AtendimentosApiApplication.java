package br.ind.scenario.suporte.atendimentos_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AtendimentosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtendimentosApiApplication.class, args);
	}
}
