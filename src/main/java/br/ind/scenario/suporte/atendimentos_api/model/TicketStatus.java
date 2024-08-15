package br.ind.scenario.suporte.atendimentos_api.model;

public enum TicketStatus {
    NOVO("Novo"),
    EM_ANDAMENTO("Em andamento"),
    PENDENTE("Pendente"),
    RESOLVIDO("Resolvido"),
    CANCELADO("Cancelado"),
    SEM_STATUS(null);

    private String statusEmPortugues;

    TicketStatus(String statusEmPortugues){
        this.statusEmPortugues = statusEmPortugues;
    }

    public static TicketStatus fromString(String text){
        if (text == null){
            return TicketStatus.SEM_STATUS;
        }
        for (TicketStatus status : TicketStatus.values()){
            if(status.statusEmPortugues.equalsIgnoreCase(text)){
                return status;
            }
        }
        throw new IllegalArgumentException("Nenhum status encontrado para a string fornecida: " + text);
    }
}

//package br.com.alura.screenmatch.model;
//
//public enum Categoria {
//    ACAO ("Action", "Ação"),
//    ROMANCE ("Romance", "Romance"),
//    COMEDIA ("Comedy", "Comédia"),
//    DRAMA ("Drama", "Drama"),
//    CRIME ("Crime", "Crime");
//
//    private String categoriaOmdb;
//    private String categoriaPortugues;
//
//    Categoria (String categoriaOmdb, String categoriaPortugues) {
//        this.categoriaOmdb = categoriaOmdb;
//        this.categoriaPortugues = categoriaPortugues;
//    }
//
//    public static Categoria fromString(String text) {
//        for (Categoria categoria : Categoria.values()) {
//            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
//                return categoria;
//            }
//        }
//        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
//    }
//
//    public static Categoria fromPortugues(String genero) {
//        for (Categoria categoria : Categoria.values()) {
//            if (categoria.categoriaPortugues.equalsIgnoreCase(genero)) {
//                return categoria;
//            }
//        }
//
//        throw new IllegalArgumentException("nenhum genero encontrado.");
//    }
//}
