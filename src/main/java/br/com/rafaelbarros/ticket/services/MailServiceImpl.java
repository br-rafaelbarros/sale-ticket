package br.com.rafaelbarros.ticket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("MailService")
public class MailServiceImpl implements MailService {

  @Autowired
  private JavaMailSender mailSender;

  @Override
  public void sendMail(String to, String subject, String body) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(to);
    mailMessage.setSubject(subject);
    mailMessage.setText(body);
    mailMessage.setFrom("ticketsale@email.com");
    mailSender.send(mailMessage);
  }

}