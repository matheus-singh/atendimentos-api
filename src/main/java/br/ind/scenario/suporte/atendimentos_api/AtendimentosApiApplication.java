package br.ind.scenario.suporte.atendimentos_api;

import br.ind.scenario.suporte.atendimentos_api.service.data.IDataConverter;
import br.ind.scenario.suporte.atendimentos_api.service.octa.ConsumoOctadeskAPI;
import br.ind.scenario.suporte.atendimentos_api.service.repository.TicketRepository;
import br.ind.scenario.suporte.atendimentos_api.service.ticket.TicketSyncService;
import br.ind.scenario.suporte.atendimentos_api.test.CommandLineTestApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AtendimentosApiApplication implements CommandLineRunner {

	@Autowired
	private IDataConverter dataConverter;

	@Autowired
	private ConsumoOctadeskAPI octaAPI;

	@Autowired
	private TicketRepository ticketRep;

	@Autowired
	private TicketSyncService ticketSyncService;

	public static void main(String[] args) {
		SpringApplication.run(AtendimentosApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CommandLineTestApp app = new CommandLineTestApp(dataConverter, octaAPI, ticketRep, ticketSyncService);
		app.run();
	}
}
