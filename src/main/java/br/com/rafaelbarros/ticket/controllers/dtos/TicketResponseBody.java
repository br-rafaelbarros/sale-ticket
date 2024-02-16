package br.com.rafaelbarros.ticket.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseBody {
  private Long id;
  private String title;
  private Integer status;
  private BigDecimal price;
  private Integer qty_available;
}
