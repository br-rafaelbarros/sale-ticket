package br.com.rafaelbarros.ticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.rafaelbarros.ticket.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.commons.PaginationFilter;
import br.com.rafaelbarros.ticket.controllers.dtos.TicketResponseBody;
import br.com.rafaelbarros.ticket.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Tickets", description = "Tickets API")
@AllArgsConstructor
@RestController
@RequestMapping("/v1/tickets")
@CrossOrigin(origins = "*")
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Pagination.class), mediaType = "application/json"))
  @Operation(summary = "List available tickets", description = "The attribute data of the response is a list of tickets. To see atributtes of ticket, see the TicketResponseBody class.")
  @GetMapping()
  public ResponseEntity<Pagination<TicketResponseBody>> listAvailable(
      @RequestParam(required = false) final String index,
      @RequestParam(required = false, defaultValue = "1") final int page,
      @RequestParam(required = false, defaultValue = "10") final int size,
      @RequestParam(required = false, defaultValue = "id") final String sort,
      @RequestParam(required = false, defaultValue = "desc") final String sort_order) {

    try {

      final PaginationFilter filterDTO = PaginationFilter.builder()
          .page(page)
          .size(size)
          .sort(sort)
          .sort_order(sort_order)
          .build();
      Pagination<TicketResponseBody> response = ticketService.listAvailable(filterDTO);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Pagination.<TicketResponseBody>builder()
              .error("Falha inesperada no sistema tente novamente mais tarde ou entre em contato com o suporte.")
              .build());
    } catch (BusinessException | InternalError e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(Pagination.<TicketResponseBody>builder().error(e.getMessage()).build());
    }
  }

  @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = TicketResponseBody.class), mediaType = "application/json"))
  @Operation(summary = "Get ticket by id", description = "Get a ticket by id", responses = {
      @ApiResponse(responseCode = "200", description = "Ticket found"),
      @ApiResponse(responseCode = "400", description = "Bad request"),
      @ApiResponse(responseCode = "404", description = "Ticket not found"),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = String.class))) })

  @GetMapping("/{id}")
  public ResponseEntity<TicketResponseBody> getTicketById(@RequestParam final Long id) {
    TicketResponseBody response = ticketService.getTicketById(id);
    return ResponseEntity.ok(response);
  }

}
