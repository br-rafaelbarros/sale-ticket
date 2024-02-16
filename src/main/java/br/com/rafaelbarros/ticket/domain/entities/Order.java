package br.com.rafaelbarros.ticket.domain.entities;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ticket_system.orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Client.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
  private Client client;

  @ManyToOne(cascade = CascadeType.ALL, targetEntity = Ticket.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false)
  private Ticket ticket;

  @Column(name = "qty", nullable = false)
  private Integer qty;

  @Column(name = "total", nullable = false, precision = 10, scale = 2)
  private BigDecimal total;

  @Column(name = "status", nullable = false, length = 1)
  private Integer status;

  @Column(name = "pix_emv", length = 255)
  private String pixEmv;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private String createdAt;

}