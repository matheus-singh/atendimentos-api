package br.ind.scenario.suporte.atendimentos_api.service.repository;

import br.ind.scenario.suporte.atendimentos_api.model.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t from Ticket t WHERE t.numero = :number")
    Ticket findByNumber(Long number);

    // Formato de data correto: YYYY-MM-DD
    @Query("SELECT t from Ticket t WHERE t.dataDeCriacao >= :date")
    List<Ticket> findByDate(LocalDate date);

    @Query("SELECT t FROM Ticket t WHERE t.numero = (SELECT MAX(t2.numero) FROM Ticket t2)")
    Optional<Ticket> getUltimoTicket();

    @Query("SELECT t FROM Ticket t WHERE t.numero = (SELECT MIN(t2.numero) FROM Ticket t2)")
    Optional<Ticket> getPrimeiroTicket();
}
