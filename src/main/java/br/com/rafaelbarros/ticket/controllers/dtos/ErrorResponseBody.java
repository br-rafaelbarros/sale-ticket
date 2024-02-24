package br.com.rafaelbarros.ticket.controllers.dtos;

public class ErrorResponseBody {
  String errorCode;
  String errorMessage;

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

}
