package br.ind.scenario.suporte.atendimentos_api;

import br.ind.scenario.suporte.atendimentos_api.test.InteraçõesCmd;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtendimentosApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AtendimentosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		InteraçõesCmd.run();
	}
}
