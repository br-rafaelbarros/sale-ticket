package br.com.rafaelbarros.ticket.services;

import br.com.rafaelbarros.ticket.controllers.dtos.OrderPaymentPixResponseBody;
import br.com.rafaelbarros.ticket.exceptions.BusinessException;

public interface OrderService {

  OrderPaymentPixResponseBody generatePaymentOrder(int ticketID, int clientID, int qtyTickets)
      throws BusinessException, InternalError;

  OrderPaymentPixResponseBody getPaymentOrder(int orderID) throws BusinessException, InternalError;

}
