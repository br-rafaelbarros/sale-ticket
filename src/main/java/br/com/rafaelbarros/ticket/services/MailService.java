package br.com.rafaelbarros.ticket.services;

public interface MailService {

  void sendMail(String to, String subject, String body);

}
