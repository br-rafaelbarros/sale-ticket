package br.com.rafaelbarros.ticket.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
  private Long id;
  private Long clientId;
  private Long ticketId;
  private Integer qty;
  private BigDecimal total;
  private Integer status;
  private String pixEmv;
  private String createdAt;
  private String updatedAt;
  private String externalId;
}
