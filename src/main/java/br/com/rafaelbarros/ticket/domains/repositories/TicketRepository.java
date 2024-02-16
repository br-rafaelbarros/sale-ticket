package br.com.rafaelbarros.ticket.domains.repositories;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.domains.dtos.TicketModel;

public interface TicketRepository {

  Pagination<TicketModel> listAvailable(PaginationFilter filters);

}
