package br.ind.scenario.suporte.atendimentos_api.test;

import br.ind.scenario.suporte.atendimentos_api.model.records.TicketSearchData;
import br.ind.scenario.suporte.atendimentos_api.service.data.IDataConverter;
import br.ind.scenario.suporte.atendimentos_api.service.octa.ConsumoOctadeskAPI;
import br.ind.scenario.suporte.atendimentos_api.service.octa.OctaAPIConnection;

import java.util.Scanner;

public class InteracoesCmd {
    private static final Scanner SCANNER = new Scanner(System.in);
    private IDataConverter dataConverter;
    private ConsumoOctadeskAPI octadeskAPI;

    public InteracoesCmd(IDataConverter dataConverter, ConsumoOctadeskAPI octadeskAPI) {
        this.dataConverter = dataConverter;
        this.octadeskAPI = octadeskAPI;
    }

    public void run() {
        String menuMessage = """
                Operacoes:
                1- Buscar um ticket pelo numero
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
                    findTicket();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida, tente novamente.");
            }
        }
    }

    private void findTicket(){
        System.out.println("Digite o numero do ticket:");
        Integer number = SCANNER.nextInt();
        SCANNER.nextLine();
        String ticketJson = octadeskAPI.getTicket(number);

        TicketSearchData ticketFound = dataConverter.stringToJson(ticketJson, TicketSearchData.class);
        System.out.println(ticketFound);
    }
}
