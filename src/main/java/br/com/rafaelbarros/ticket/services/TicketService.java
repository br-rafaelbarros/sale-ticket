package br.com.rafaelbarros.ticket.services;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.controllers.dtos.TicketResponseBody;

public interface TicketService {

  Pagination<TicketResponseBody> listAvailable(PaginationFilter filters);

}
