package ru.safronov.mySpringProject.Library.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safronov.mySpringProject.Library.model.Book;
import ru.safronov.mySpringProject.Library.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public List<Book> getAllBooks() {
    return bookRepository.getBooks();
  }

  public Book getBookById(long id) {
    return findExistsBook(id);
  }

  public List<Book> deleteBook(long id) {
    Book book = findExistsBook(id);
    return bookRepository.deleteBook(book);
  }

  public Book addBook(String name) {
    return bookRepository.addBook(name);
  }

  private Book findExistsBook(long id) {
    Book book = bookRepository.getBookById(id);
    if (book == null) {
      throw new NoSuchElementException("Книга под id: " + id + " в библиотеке не найдена!");
    }
    return book;
  }
}
