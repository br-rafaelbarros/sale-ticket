package br.com.rafaelbarros.ticket.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("OrderProducer")
public class OrderProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {
    log.info("Payload enviado: {}" + message);
    kafkaTemplate.send(topic, message);
  }
}
