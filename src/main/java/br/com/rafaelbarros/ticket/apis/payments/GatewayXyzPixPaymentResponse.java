package br.com.rafaelbarros.ticket.apis.payments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GatewayXyzPixPaymentResponse {
  private String qrCode;
  private String paymentLink;
  private String expirationDate;
  private String paymentId;
  private BigDecimal amount;
}
