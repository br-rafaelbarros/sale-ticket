package br.com.rafaelbarros.ticket.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rafaelbarros.ticket.apis.payments.AdapterGatewayXyz;
import br.com.rafaelbarros.ticket.apis.payments.PixPaymentDTO;
import br.com.rafaelbarros.ticket.apis.payments.PixPaymentRequest;
import br.com.rafaelbarros.ticket.controllers.dtos.OrderPaymentPixResponseBody;
import br.com.rafaelbarros.ticket.controllers.dtos.TicketResponseBody;
import br.com.rafaelbarros.ticket.domains.dtos.OrderModel;
import br.com.rafaelbarros.ticket.domains.repositories.OrderRepository;
import br.com.rafaelbarros.ticket.exceptions.BusinessException;

import org.springframework.stereotype.Service;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {

  @Autowired
  private AdapterGatewayXyz adapterGatewayXyz;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private MailService mailService;

  @Override
  public OrderPaymentPixResponseBody generatePaymentOrder(
      int ticketID, int clientID, int qtyTickets) throws BusinessException, InternalError {

    TicketResponseBody ticket = ticketService.getTicketById(Long.valueOf(ticketID));
    BigDecimal amount = ticket.getPrice().multiply(new BigDecimal(qtyTickets));
    PixPaymentDTO pixPayment = getPixPaymentMethod(clientID, amount);
    OrderModel order = orderRepository.saveOrder(ticketID, clientID, qtyTickets, amount,
        pixPayment.getPaymentId(), pixPayment.getQrCode());

    OrderPaymentPixResponseBody orderTicket = OrderPaymentPixResponseBody.builder()
        .id(order.getId())
        .title(ticket.getTitle())
        .status(order.getStatus())
        .price(amount)
        .qtyTicket(order.getQty())
        .qrCode(pixPayment.getQrCode())
        .urlLink(pixPayment.getPaymentLink())
        .build();
    try {
      mailService.sendMail(
          "br.rafaelbarros@gmail.com",
          "Compra de ingresso (" + ticket.getTitle() + ")",
          "O pedido de compra foi gerado com sucesso, acesse o link para efetuar o pagamento: "
              + pixPayment.getPaymentLink());
    } catch (Exception e) {
      System.out.println("Erro ao enviar email: " + e.getMessage());
    }

    return orderTicket;
  }

  private PixPaymentDTO getPixPaymentMethod(int clientID, BigDecimal amount) {
    PixPaymentRequest pixPaymentRequest = PixPaymentRequest.builder()
        .amount(amount)
        .customerID(String.valueOf(clientID))
        .expirationDate("2021-12-31 23:59:59 GMT-3")
        .build();

    return adapterGatewayXyz.chargePaymentPix(pixPaymentRequest);
  }

  @Override
  public OrderPaymentPixResponseBody getPaymentOrder(int orderID) throws BusinessException, InternalError {

    OrderModel order = orderRepository.getOrder(orderID);
    TicketResponseBody ticket = ticketService.getTicketById(order.getTicketId());

    return OrderPaymentPixResponseBody.builder()
        .id(order.getId())
        .title(ticket.getTitle())
        .status(order.getStatus())
        .price(order.getTotal())
        .qtyTicket(order.getQty())
        .qrCode(order.getPixEmv())
        .urlLink("https://pix.example.com/pix/" + order.getExternalId())
        .build();

  }

}
