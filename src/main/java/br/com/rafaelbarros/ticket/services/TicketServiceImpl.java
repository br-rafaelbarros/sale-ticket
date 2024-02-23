package br.com.rafaelbarros.ticket.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.controllers.dtos.TicketResponseBody;
import br.com.rafaelbarros.ticket.domains.dtos.TicketModel;
import br.com.rafaelbarros.ticket.domains.repositories.TicketRepository;
import br.com.rafaelbarros.ticket.exceptions.BusinessException;

@Service("TicketService")
public class TicketServiceImpl implements TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  @Override
  public Pagination<TicketResponseBody> listAvailable(PaginationFilter filters)
      throws BusinessException, InternalError {
    Pagination<TicketModel> tickets = ticketRepository.listAvailable(filters);
    try {
      List<TicketResponseBody> ticketResponse = new ArrayList<TicketResponseBody>();
      tickets.getData().forEach(ticket -> {
        ticketResponse.add(TicketResponseBody.builder()
            .id(ticket.getId())
            .title(ticket.getTitle())
            .status(ticket.getStatus())
            .price(ticket.getPrice())
            .qtyAvailable(ticket.getQtyAvailable())
            .build());
      });

      Pagination<TicketResponseBody> response = Pagination.<TicketResponseBody>builder()
          .page(tickets.getPage())
          .size(tickets.getSize())
          .total(tickets.getTotal())
          .totalPages(tickets.getTotalPages())
          .data(ticketResponse)
          .build();

      return response;
    } catch (Exception e) {
      throw new BusinessException("Erro inesperado ao tentar listar os tickets disponíveis.", e);
    }
  }

  @Override
  public TicketResponseBody getTicketById(Long id) {
    TicketModel ticket = ticketRepository.getTicketById(id);
    TicketResponseBody response = TicketResponseBody.builder()
        .id(ticket.getId())
        .title(ticket.getTitle())
        .status(ticket.getStatus())
        .price(ticket.getPrice())
        .qtyAvailable(ticket.getQtyAvailable())
        .build();
    return response;
  }

}
