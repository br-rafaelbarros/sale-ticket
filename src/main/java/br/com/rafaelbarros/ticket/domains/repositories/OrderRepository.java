package br.com.rafaelbarros.ticket.domains.repositories;

import java.math.BigDecimal;

import br.com.rafaelbarros.ticket.domains.dtos.OrderModel;

public interface OrderRepository {

  OrderModel saveOrder(int ticketID, int clientID, int qtyTickets,
      BigDecimal amount, String paymentId, String pixEmv);

  OrderModel getOrder(int orderID);

}
