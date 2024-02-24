package br.com.rafaelbarros.ticket.apis.payments;

public final class GatewayXyz {

  public static GatewayXyzPixPaymentResponse chargePix(PixPaymentRequest request) {

    GatewayXyzPixPaymentResponse response = GatewayXyzPixPaymentResponse.builder()
        .qrCode(
            "00020101021226850014br.gov.bcb.pix2563pix.example.com/pix/2d25e3d3-0b3f-4e3d-8f3c-2f3d3b3d3b3d52040000530398654041.005802BR5925Rafael Barros6009SAO PAULO62290528.005802BR5925Rafael Barros6009SAO PAULO62290528.006304433f")
        .paymentLink("https://pix.example.com/pix/2d25e3d3-0b3f-4e3d-8f3c-2f3d3b3d3b3d")
        .expirationDate("2023-12-31T23:59:59-03:00")
        .paymentId("2d25e3d3-0b3f-4e3d-8f3c-2f3d3b3d3b3d")
        .build();
    return response;

  }

}
