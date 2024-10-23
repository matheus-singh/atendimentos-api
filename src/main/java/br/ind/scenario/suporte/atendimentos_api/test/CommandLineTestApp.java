package br.ind.scenario.suporte.atendimentos_api.test;

import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.service.data.IDataConverter;
import br.ind.scenario.suporte.atendimentos_api.service.octa.ConsumoOctadeskAPI;
import br.ind.scenario.suporte.atendimentos_api.service.repository.TicketRepository;
import br.ind.scenario.suporte.atendimentos_api.service.ticket.TicketSyncService;
import br.ind.scenario.suporte.atendimentos_api.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommandLineTestApp {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final TicketSyncService ticketSyncService;
    private final IDataConverter dataConverter;
    private final ConsumoOctadeskAPI octadeskAPI;
    private final TicketRepository ticketRepository;

    public CommandLineTestApp(IDataConverter dataConverter, ConsumoOctadeskAPI octadeskAPI, TicketRepository ticketRepository, TicketSyncService ticketSyncService) {
        this.dataConverter = dataConverter;
        this.octadeskAPI = octadeskAPI;
        this.ticketRepository = ticketRepository;
        this.ticketSyncService = ticketSyncService;
    }

    public void run() {
        String menuMessage = """
                Operacoes:
                1- Solicitar um ticket pelo numero
                2- Solicitar tickets em um intervalo
                3- Buscar ticket no banco pelo numero
                0- Sair
                """;
        int option = Integer.MAX_VALUE;
        while (option != 0){
            System.out.println(menuMessage);
            System.out.println("Digite uma opcao: ");
            option = SCANNER.nextInt();
            SCANNER.nextLine();
            switch (option){
                case 1:
                    buscarNoOctaPeloNumero();
                    break;
                case 2:
                    buscarNoOctaPorIntervaloDeNumero();
                    break;
                case 3:
                    trazerTicketDoBancoPorNumero();
                    break;
                case 0:
                    System.out.println("Fechando aplicação linha de comando.");
                    System.out.println("API rodando...");
                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente.");
                    break;
            }
        }
    }

    private void trazerTicketDoBancoPorNumero() {
        System.out.println("Digite o numero do ticket:");
        Long number = SCANNER.nextLong();
        SCANNER.nextLine();
        try{
            System.out.println("Procurando Ticket...");
            Optional<Ticket> ticketEncontrado = Optional.ofNullable(ticketRepository.findByNumber(number));

            if (ticketEncontrado.isPresent()){
                System.out.println(ticketEncontrado.get());
            } else {
                System.out.println("Ticket não encontrado.");
            }
        } catch (Exception e){
            System.out.println("Ocorreu um erro na requisicao: "+e.getMessage());
        }
    }

    private void buscarNoOctaPorIntervaloDeNumero() {
        System.out.println("Deseja buscar a partir de qual ticket? Digete o numero: ");
        int numero = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println("Deseja buscar ate qual ticket? Digete o numero: ");
        int numeroFim = SCANNER.nextInt();
        SCANNER.nextLine();
        List<Ticket> listaDeTicketsEncontrados = new ArrayList<>();
        int counter = 0;
        try{
            System.out.println("Buscando tickets...");
            for (int i = numero; i<=numeroFim; i++){
                String ticketJson = octadeskAPI.getTicket(i);
                TicketSearchData ticketFound = dataConverter.stringToJson(ticketJson, TicketSearchData.class);
                System.out.println(ticketFound);
                Ticket ticket = new Ticket(ticketFound);
                System.out.println("Ticket encontrado! Numero - " + i);
                listaDeTicketsEncontrados.add(ticket);
                counter++;
                if (counter == 500){
                    try {
                        listaDeTicketsEncontrados.forEach(this::saveTicket);
                        System.out.println(" 1000 Tickets Saved and Updated. Returning...");
                    } catch (Exception e) {
                        System.out.println("An error has occurred: {}" + e.getMessage());
                    }
                    counter = 0;
                    listaDeTicketsEncontrados.clear();
                }
            }
        } catch (Exception e){
            System.out.println("Ocorreu um erro na requisicao: "+e.getMessage());
        }
        if (!listaDeTicketsEncontrados.isEmpty()){
            try {
                listaDeTicketsEncontrados.forEach(this::saveTicket);
                System.out.println(" Remaining Tickets Saved and Updated. Returning...");
            } catch (Exception e) {
                System.out.println("An error has occurred: {}" + e.getMessage());
            }
        }
    }

    private void buscarNoOctaPeloNumero(){
        System.out.println("Digite o numero do ticket:");
        Integer number = SCANNER.nextInt();
        SCANNER.nextLine();
        try{
            System.out.println("Procurando Ticket...");
            String ticketJson = octadeskAPI.getTicket(number);
            System.out.println("Deseja Imprimir o Json? y/n");
            String resposta = SCANNER.nextLine();
            if (resposta.equalsIgnoreCase("y")){
                JsonUtils.prettyPrintJson(ticketJson);
            }
            TicketSearchData ticketFound = dataConverter.stringToJson(ticketJson, TicketSearchData.class);
            System.out.println(ticketFound);
            Ticket ticket = new Ticket(ticketFound);
            System.out.println("Ticket encontrado!");


            saveTicket(ticket);
            System.out.println(ticket);
        } catch (Exception e){
            System.out.println("Ocorreu um erro na requisicao: "+e.getMessage());
        }
    }

    private void saveTicket(Ticket ticket){
        Optional<Ticket> existingTicketOpt = Optional.ofNullable(ticketRepository.findByNumber(ticket.getNumero()));
        if (existingTicketOpt.isPresent() && !(existingTicketOpt.get().getOctaId() == null)) {
            Ticket existingTicket = existingTicketOpt.get();
            ticketSyncService.updateExistingTicket(existingTicket, ticket);
            ticketRepository.save(existingTicket);
        } else {
            if (!(ticket.getOctaId() == null)){
                ticketRepository.save(ticket);
            }
        }
    }
}
