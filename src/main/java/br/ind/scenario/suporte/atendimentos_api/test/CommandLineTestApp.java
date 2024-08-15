package br.ind.scenario.suporte.atendimentos_api.test;

import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.service.data.IDataConverter;
import br.ind.scenario.suporte.atendimentos_api.service.octa.ConsumoOctadeskAPI;
import br.ind.scenario.suporte.atendimentos_api.service.repository.TicketRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommandLineTestApp {
    private static final Scanner SCANNER = new Scanner(System.in);
    private IDataConverter dataConverter;
    private ConsumoOctadeskAPI octadeskAPI;
    private TicketRepository ticketRepository;

    public CommandLineTestApp(IDataConverter dataConverter, ConsumoOctadeskAPI octadeskAPI, TicketRepository ticketRepository) {
        this.dataConverter = dataConverter;
        this.octadeskAPI = octadeskAPI;
        this.ticketRepository = ticketRepository;
    }

    public void run() {
        String menuMessage = """
                Operacoes:
                1- Buscar um ticket pelo numero
                2- Buscar tickets da semana
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
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente.");
            }
        }
    }

    private void searchLastWeekTickets() {
        try{
            System.out.println("Buscando tickets da semana...");
            String ticketsDaSemanaJson = octadeskAPI.getTicketsOfTheWeek();
            List<TicketSearchData> ticketsFound = dataConverter.stringToJsonList(ticketsDaSemanaJson, TicketSearchData.class);
            System.out.println("Tickets da semana encontrados!");

            List<Ticket> tickets = ticketsFound.stream().map(Ticket::new).collect(Collectors.toList());
            tickets.forEach(ticket -> ticketRepository.save(ticket));
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
            TicketSearchData ticketFound = dataConverter.stringToJson(ticketJson, TicketSearchData.class);
            Ticket ticket = new Ticket(ticketFound);
            System.out.println("Ticket encontrado!");

            ticketRepository.save(ticket);
            System.out.println(ticket);
        } catch (Exception e){
            System.out.println("Ocorreu um erro na requisicao: "+e.getMessage());
        }
    }
}
