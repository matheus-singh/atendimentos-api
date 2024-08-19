package br.ind.scenario.suporte.atendimentos_api.model;

import java.util.Optional;

public enum TicketStatus {
    NOVO("Novo"),
    EM_ANDAMENTO("Em andamento"),
    PENDENTE("Pendente"),
    RESOLVIDO("Resolvido"),
    CANCELADO("Cancelado"),
    SEM_STATUS(null);

    private final String statusEmPortugues;

    TicketStatus(String statusEmPortugues) {
        this.statusEmPortugues = statusEmPortugues;
    }

    public static TicketStatus fromString(String text) {
        return findByPortugueseName(text).orElse(TicketStatus.SEM_STATUS);
    }

    private static Optional<TicketStatus> findByPortugueseName(String text) {
        for (TicketStatus status : TicketStatus.values()) {
            if (status.statusEmPortugues != null && status.statusEmPortugues.equalsIgnoreCase(text)) {
                return Optional.of(status);
            }
        }
        return Optional.empty();
    }
}
