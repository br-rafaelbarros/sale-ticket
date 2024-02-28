package br.com.rafaelbarros.ticket.messages;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("OrderConsumer")
public class OrderConsumer {

  private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

  @KafkaListener(topics = "topicTicketPayment")
  public void consume(String message) {
    logger.info(String.format("1) Mensagem recebida -> %s", message));
    log.info(String.format("2) Mensagem recebida -> %s", message));
  }

}
