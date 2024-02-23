package br.com.rafaelbarros.ticket.exceptions;

public class InternalError extends Exception {

  private static final long serialVersionUID = 1L;

  public InternalError(String message) {
    super(message);
  }

  public InternalError(String message, Throwable cause) {
    super(message, cause);
  }
}
