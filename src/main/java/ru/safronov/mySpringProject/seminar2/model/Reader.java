package ru.safronov.mySpringProject.seminar2.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Reader {

  public static long sequence = 1L;
  private final long id;
  private final String name;
  /**
   * Количество книг у пользователя
   */
  private int booksCount;
  /**
   * Максимально возможное количество книг на руках
   */
  @Value("${application.issue.max-allowed-books:1}")
  private int maxAllowedBooks;
  /**
   * Список выдачей пользователя
   */
  private List<Issue> issueList;

  public Reader(String name) {
    this.id = sequence++;
    this.name = name;
    this.maxAllowedBooks = 3;
    this.issueList = new ArrayList<>();
  }

}
