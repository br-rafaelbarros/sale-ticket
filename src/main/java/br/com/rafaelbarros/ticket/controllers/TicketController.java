package br.com.rafaelbarros.ticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.rafaelbarros.ticket.services.TicketService;
import lombok.AllArgsConstructor;
import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.controllers.dtos.TicketResponseBody;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @GetMapping()
  public ResponseEntity<Pagination<TicketResponseBody>> listAvailable(
      @RequestParam(required = false) final String index,
      @RequestParam(required = false, defaultValue = "1") final int page,
      @RequestParam(required = false, defaultValue = "10") final int size,
      @RequestParam(required = false, defaultValue = "id") final String sort,
      @RequestParam(required = false, defaultValue = "desc") final String sort_order) {

    final PaginationFilter filterDTO = PaginationFilter.builder()
        .page(page)
        .size(size)
        .sort(sort)
        .sort_order(sort_order)
        .build();
    Pagination<TicketResponseBody> response = ticketService.listAvailable(filterDTO);
    return ResponseEntity.ok(response);
  }

}
