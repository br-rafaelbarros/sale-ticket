package br.com.rafaelbarros.ticket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelbarros.ticket.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import br.com.rafaelbarros.ticket.commons.Pagination;
import br.com.rafaelbarros.ticket.controllers.dtos.OrderPaymentPixResponseBody;
import br.com.rafaelbarros.ticket.exceptions.BusinessException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Orders", description = "Orders API")
@AllArgsConstructor
@RestController
@RequestMapping("/v1/orders")
@CrossOrigin(origins = "*")
public class OrderController {

  @Autowired
  private OrderService service;

  @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Pagination.class), mediaType = "application/json"))
  @Operation(summary = "Generate payment order", description = "Return data of payment to buy tickets")
  @PostMapping("/payment-order")
  public ResponseEntity<OrderPaymentPixResponseBody> generatePaymentOrder(
      @RequestParam(required = false) final String index,
      @RequestParam(required = false, defaultValue = "1") final int ticketID,
      @RequestParam(required = false, defaultValue = "10") final int clientID,
      @RequestParam(required = false, defaultValue = "2") final int qtyTickets) throws Throwable {

    try {
      OrderPaymentPixResponseBody response = service.generatePaymentOrder(ticketID, clientID, qtyTickets);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      OrderPaymentPixResponseBody responseError = OrderPaymentPixResponseBody.builder().build();
      responseError
          .setErrorMessage("Falha inesperada no sistema tente novamente mais tarde ou entre em contato com o suporte.");
      responseError.setErrorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    } catch (BusinessException | InternalError e) {
      OrderPaymentPixResponseBody responseError = OrderPaymentPixResponseBody.builder().build();
      responseError.setErrorMessage(e.getMessage());
      responseError.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
  }

  @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Pagination.class), mediaType = "application/json"))
  @Operation(summary = "Get payment order", description = "Return data of payment to buy tickets")
  @GetMapping("/payment-order")
  public ResponseEntity<OrderPaymentPixResponseBody> getPaymentOrder(
      @RequestParam(required = false, defaultValue = "1") final int orderID) throws Throwable {

    try {
      OrderPaymentPixResponseBody response = service.getPaymentOrder(orderID);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      OrderPaymentPixResponseBody responseError = OrderPaymentPixResponseBody.builder().build();
      responseError
          .setErrorMessage("Falha inesperada no sistema tente novamente mais tarde ou entre em contato com o suporte.");
      responseError.setErrorCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    } catch (BusinessException | InternalError e) {
      OrderPaymentPixResponseBody responseError = OrderPaymentPixResponseBody.builder().build();
      responseError.setErrorMessage(e.getMessage());
      responseError.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }
  }

}
