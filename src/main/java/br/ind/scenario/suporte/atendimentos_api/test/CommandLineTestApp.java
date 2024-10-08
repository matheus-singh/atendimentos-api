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
import java.util.stream.Collectors;

public class CommandLineTestApp {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final TicketSyncService ticketSyncService;
    private IDataConverter dataConverter;
    private ConsumoOctadeskAPI octadeskAPI;
    private TicketRepository ticketRepository;

    public CommandLineTestApp(IDataConverter dataConverter, ConsumoOctadeskAPI octadeskAPI, TicketRepository ticketRepository, TicketSyncService ticketSyncService) {
        this.dataConverter = dataConverter;
        this.octadeskAPI = octadeskAPI;
        this.ticketRepository = ticketRepository;
        this.ticketSyncService = ticketSyncService;
    }

    public void run() {
        String menuMessage = """
                Operacoes:
                1- Buscar um ticket pelo numero
                2- Buscar tickets da semana
                3- Buscar tickets em um intervalo
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
                    searchTicketByNumber();
                    break;
                case 2:
                    searchLastWeekTickets();
                    break;
                case 3:
                    buscarTicketsEmUmIntervalo();
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

    private void buscarTicketsEmUmIntervalo() {
        System.out.println("Deseja buscar a partir de qual ticket? Digete o numero: ");
        int numero = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println("Deseja buscar ate qual ticket? Digete o numero: ");
        int numeroFim = SCANNER.nextInt();
        SCANNER.nextLine();
        List<Ticket> listaDeTicketsEncontrados = new ArrayList<>();
        try{
            System.out.println("Buscando tickets...");
            for (int i = numero; i<=numeroFim; i++){
                String ticketJson = octadeskAPI.getTicket(i);
                TicketSearchData ticketFound = dataConverter.stringToJson(ticketJson, TicketSearchData.class);
                System.out.println(ticketFound);
                Ticket ticket = new Ticket(ticketFound);
                System.out.println("Ticket encontrado! Numero - " + i);
                listaDeTicketsEncontrados.add(ticket);
            }
        } catch (Exception e){
            System.out.println("Ocorreu um erro na requisicao: "+e.getMessage());
        }
        try {
            listaDeTicketsEncontrados.forEach(this::saveOrUpdateTicket);
            System.out.println("Tickets Saved and Updated. Returning...");
        } catch (Exception e) {
            System.out.println("An error has occurred: {}" + e.getMessage());
        }
    }

    private void searchLastWeekTickets() {
        try{
            System.out.println("Buscando tickets da semana...");
            String ticketsDaSemanaJson = octadeskAPI.getTicketsOfTheWeek();
            List<TicketSearchData> ticketsFound = dataConverter.stringToJsonList(ticketsDaSemanaJson, TicketSearchData.class);
            System.out.println("Tickets da semana encontrados!");

            List<Ticket> tickets = ticketsFound.stream().map(Ticket::new).collect(Collectors.toList());
            tickets.forEach(this::saveOrUpdateTicket);
            System.out.println("Total tickets of the week: "+Long.valueOf(tickets.size()));
        } catch (Exception e){
            System.out.println("Ocorreu um erro na requisicao: "+e.getMessage());
        }
    }

    private void searchTicketByNumber(){
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

            saveOrUpdateTicket(ticket);
            System.out.println(ticket);
        } catch (Exception e){
            System.out.println("Ocorreu um erro na requisicao: "+e.getMessage());
        }
    }

    private void saveOrUpdateTicket(Ticket ticket){
        Optional<Ticket> existingTicketOpt = ticketRepository.findByOctaId(ticket.getOctaId());
        if (existingTicketOpt.isPresent()) {
            Ticket existingTicket = existingTicketOpt.get();
            ticketSyncService.updateExistingTicket(existingTicket, ticket);
            ticketRepository.save(existingTicket);
        } else {
            ticketRepository.save(ticket);
        }
    }
}
