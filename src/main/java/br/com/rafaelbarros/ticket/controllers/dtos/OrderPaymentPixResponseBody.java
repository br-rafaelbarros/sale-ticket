package br.com.rafaelbarros.ticket.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderPaymentPixResponseBody extends ErrorResponseBody {

  private Long id;
  private String title;
  private Integer status;
  private BigDecimal price;
  @JsonProperty("qty_ticket")
  private Integer qtyTicket;
  @JsonProperty("qr_code")
  private String qrCode;
  @JsonProperty("url_link")
  private String urlLink;

}
