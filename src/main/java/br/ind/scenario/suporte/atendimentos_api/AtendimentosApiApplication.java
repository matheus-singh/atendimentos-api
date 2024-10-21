package br.ind.scenario.suporte.atendimentos_api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AtendimentosApiApplication {

//	@Autowired
//	private IDataConverter dataConverter;
//
//	@Autowired
//	private ConsumoOctadeskAPI octaAPI;
//
//	@Autowired
//	private TicketRepository ticketRep;
//
//	@Autowired
//	private TicketSyncService ticketSyncService;

	public static void main(String[] args) {
		SpringApplication.run(AtendimentosApiApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		CommandLineTestApp app = new CommandLineTestApp(dataConverter, octaAPI, ticketRep, ticketSyncService);
//		app.run();
//	}
}
