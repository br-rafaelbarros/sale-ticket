package br.com.rafaelbarros.ticket.apis.payments;

import org.springframework.stereotype.Service;

@Service
public class AdapterGatewayXyz {

  public PixPaymentDTO chargePaymentPix(PixPaymentRequest pixPaymentRequest) {

    GatewayXyzPixPaymentResponse response = GatewayXyz.chargePix(pixPaymentRequest);

    String paymentId = Math.random() + "";
    paymentId = paymentId.substring(2, 12);

    PixPaymentDTO returnDTO = PixPaymentDTO.builder()
        .qrCode(response.getQrCode())
        .paymentLink(response.getPaymentLink())
        .expirationDate(response.getExpirationDate())
        .paymentId(paymentId)
        .amount(response.getAmount())
        .build();
    return returnDTO;
  }

}
