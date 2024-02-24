package br.com.rafaelbarros.ticket.domains.repositories;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelbarros.ticket.domains.dtos.OrderModel;
import br.com.rafaelbarros.ticket.domains.entities.Client;
import br.com.rafaelbarros.ticket.domains.entities.Order;
import br.com.rafaelbarros.ticket.domains.entities.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository("OrderRepository")
public class OrderRepositoryImpl implements OrderRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private OrderJpaRepository jpaRepository;

  @Override
  public OrderModel saveOrder(int ticketID, int clientID, int qtyTickets,
      BigDecimal amount, String paymentId, String pixEmv) {

    try {
      Date now = new Date();
      Order order = jpaRepository.save(Order.builder()
          .client(Client.builder().id(Long.valueOf(clientID)).build())
          .ticket(Ticket.builder().id(Long.valueOf(ticketID)).build())
          .qty(qtyTickets)
          .total(amount)
          .status(1)
          .pixEmv(pixEmv)
          .externalId(paymentId)
          .createdAt(now)
          .updatedAt(now)
          .build());

      return OrderModel.builder()
          .id(order.getId())
          .clientId(order.getClient().getId())
          .ticketId(order.getTicket().getId())
          .qty(order.getQty())
          .total(order.getTotal())
          .status(order.getStatus())
          .pixEmv(order.getPixEmv())
          .createdAt(order.getCreatedAt().toString())
          .updatedAt(order.getUpdatedAt().toString())
          .externalId(order.getExternalId())
          .build();

    } catch (Exception e) {
      System.out.println(e.getStackTrace());
      throw new RuntimeException("Error to save order", e);
    }

  }

  @Override
  public OrderModel getOrder(int orderID) {
    Order order = jpaRepository.findById(Long.valueOf(orderID)).get();

    return OrderModel.builder()
        .id(order.getId())
        .clientId(order.getClient().getId())
        .ticketId(order.getTicket().getId())
        .qty(order.getQty())
        .total(order.getTotal())
        .status(order.getStatus())
        .pixEmv(order.getPixEmv())
        .createdAt(order.getCreatedAt().toString())
        .updatedAt(order.getUpdatedAt().toString())
        .externalId(order.getExternalId())
        .build();
  }

}

@Repository("OrderJpaRepository")
interface OrderJpaRepository extends JpaRepository<Order, Long> {
}