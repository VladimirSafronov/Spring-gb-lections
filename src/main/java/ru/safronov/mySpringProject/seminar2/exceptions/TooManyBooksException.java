package ru.safronov.mySpringProject.seminar2.exceptions;

public class TooManyBooksException extends RuntimeException {

  public TooManyBooksException(String message) {
    super(message);
  }
}
