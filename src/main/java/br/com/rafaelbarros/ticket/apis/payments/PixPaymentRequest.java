package br.com.rafaelbarros.ticket.apis.payments;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixPaymentRequest {
  private String customerID;
  private BigDecimal amount;
  private String expirationDate;
}
