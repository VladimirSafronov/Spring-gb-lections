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
   * Список выдачей пользователя
   */
  private List<Issue> issueList;

  public Reader(String name) {
    this.id = sequence++;
    this.name = name;
    this.issueList = new ArrayList<>();
  }

}
