package br.ind.scenario.suporte.atendimentos_api;

import br.ind.scenario.suporte.atendimentos_api.service.data.DataConverter;
import br.ind.scenario.suporte.atendimentos_api.service.data.IDataConverter;
import br.ind.scenario.suporte.atendimentos_api.service.octa.ConsumoOctadeskAPI;
import br.ind.scenario.suporte.atendimentos_api.service.octa.OctaAPIConnection;
import br.ind.scenario.suporte.atendimentos_api.test.InteracoesCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AtendimentosApiApplication implements CommandLineRunner {

	@Autowired
	private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(AtendimentosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		IDataConverter dataConverter = new DataConverter();
		OctaAPIConnection connection = new OctaAPIConnection();
		ConsumoOctadeskAPI octadeskAPI = new ConsumoOctadeskAPI(connection);

		InteracoesCmd cmdApp = new InteracoesCmd(dataConverter, octadeskAPI);
		cmdApp.run();
		System.exit(SpringApplication.exit(context, () -> 0));
	}
}
