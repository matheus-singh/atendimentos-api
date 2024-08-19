package br.ind.scenario.suporte.atendimentos_api.service.repository;

import br.ind.scenario.suporte.atendimentos_api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByOctaId(String octaId);

    @Query("SELECT t from Ticket t WHERE t.numero = :number")
    Ticket findByNumber(Integer number);

    @Query("SELECT t from Ticket t WHERE t.dataDeCriacao >= :date")
    List<Ticket> findByDate(LocalDate date);
}
