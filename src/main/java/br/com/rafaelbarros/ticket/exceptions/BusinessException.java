package br.com.rafaelbarros.ticket.exceptions;

public class BusinessException extends Throwable {

  private static final long serialVersionUID = 1L;

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

}
