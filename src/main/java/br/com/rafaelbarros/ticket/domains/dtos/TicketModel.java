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
public class TicketModel {
  private Long id;
  private String title;
  private Integer status;
  private BigDecimal price;
  private Integer qtyAvailable;
}
