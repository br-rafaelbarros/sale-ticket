package br.com.rafaelbarros.ticket.services;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.controllers.dtos.TicketResponseBody;
import br.com.rafaelbarros.ticket.exceptions.BusinessException;

public interface TicketService {

  Pagination<TicketResponseBody> listAvailable(PaginationFilter filters) throws BusinessException, InternalError;

  TicketResponseBody getTicketById(Long id);

}
