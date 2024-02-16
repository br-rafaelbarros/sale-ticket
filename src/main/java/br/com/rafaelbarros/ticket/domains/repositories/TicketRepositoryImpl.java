package br.com.rafaelbarros.ticket.domains.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.domains.dtos.TicketModel;
import br.com.rafaelbarros.ticket.domains.entities.Ticket;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

@Repository("TicketRepository")
public class TicketRepositoryImpl implements TicketRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Pagination<TicketModel> listAvailable(PaginationFilter filters) {

    filters = loadCommonFilters(filters);

    Integer totalItems = entityManager.createQuery(
        "SELECT count (t) FROM tickets t WHERE t.qtyAvailable > 0 AND t.status = 1", Long.class).getSingleResult()
        .intValue();

    String queryScript = " SELECT t FROM tickets t WHERE t.qtyAvailable > 0 AND t.status = 1 "
        + " ORDER BY t." + filters.getSort() + " " + filters.getSort_order()
        + " LIMIT " + filters.getSize() + " OFFSET " + ((filters.getPage() - 1) * filters.getSize());

    TypedQuery<Ticket> queryResult = entityManager.createQuery(queryScript, Ticket.class);

    List<TicketModel> ticketModels = new ArrayList<TicketModel>();
    queryResult.getResultList().forEach(ticket -> {
      ticketModels.add(TicketModel.builder()
          .id(ticket.getId())
          .title(ticket.getTitle())
          .price(ticket.getPrice())
          .status(ticket.getStatus())
          .qtyAvailable(ticket.getQtyAvailable())
          .build());
    });

    return SharedRepository.buildPaginationResult(filters, totalItems, ticketModels);
  }

  private PaginationFilter loadCommonFilters(PaginationFilter filters) {
    return PaginationFilter.builder()
        .page((filters.getPage() == null || filters.getPage() == 0) ? 1 : filters.getPage())
        .size(filters.getSize() == null ? 10 : filters.getSize())
        .sort(filters.getSort() == null ? "id" : filters.getSort())
        .sort_order(
            (filters.getSort_order() != null && filters.getSort_order().toLowerCase().equals("desc")) ? "desc" : "asc")
        .build();
  }
}
