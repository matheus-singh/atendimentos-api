package br.ind.scenario.suporte.atendimentos_api.service.repository;

import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByOctaId(String octaId);
}
