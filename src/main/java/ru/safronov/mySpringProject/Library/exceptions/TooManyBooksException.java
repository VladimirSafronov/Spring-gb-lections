package ru.safronov.mySpringProject.Library.exceptions;

public class TooManyBooksException extends RuntimeException {

  public TooManyBooksException(String message) {
    super(message);
  }
}
