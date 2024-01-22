package ru.safronov.mySpringProject.Library.model;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
// @Entity
public class Issue {

  public static long sequence = 1L;

  private final long id;
  private final long bookId;
  private final long readerId;

  /**
   * Дата выдачи
   */
  private final LocalDateTime issued_at;
  private LocalDateTime returned_at;

  public Issue(long bookId, long readerId) {
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued_at = LocalDateTime.now();
  }

}