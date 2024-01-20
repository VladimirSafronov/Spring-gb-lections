package ru.safronov.mySpringProject.seminar2.repository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;
import ru.safronov.mySpringProject.seminar2.model.Book;

@Repository
public class BookRepository {

  private final List<Book> books;

  public BookRepository() {
    this.books = new ArrayList<>();
  }

  /**
   * Аннотация @PostConstruct позволяет произвести какие-то действия после создания бина
   */
  @PostConstruct
  public void generateData() {
    books.addAll(List.of(
        new Book("война и мир"),
        new Book("метрвые души"),
        new Book("чистый код")
    ));
  }

  public List<Book> getBooks() {
    return books;
  }

  public Book getBookById(long id) {
    return books.stream().filter(it -> Objects.equals(it.getId(), id))
        .findFirst()
        .orElse(null);
  }

  public Book addBook(String name) {
    Book book = new Book(name);
    books.add(book);
    return book;
  }

  public List<Book> deleteBook(Book book) {
    books.remove(book);
    return books;
  }

}
