package ru.safronov.mySpringProject.Library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safronov.mySpringProject.Library.model.Book;
import ru.safronov.mySpringProject.Library.model.Issue;
import ru.safronov.mySpringProject.Library.model.Reader;
import ru.safronov.mySpringProject.Library.repository.BookRepository;
import ru.safronov.mySpringProject.Library.repository.ReaderRepository;

@Service
@RequiredArgsConstructor
public class ReaderService {

  private final ReaderRepository repository;
  private final BookRepository bookRepository;

  public Reader getReader(long id) {
    return getExistsReader(id);
  }

  public Reader createReader(String name) {
    return repository.addReader(name);
  }

  public List<Reader> deleteReader(long id) {
    Reader reader = getExistsReader(id);
    return repository.deleteReader(reader);
  }

  public List<Issue> getIssueList(long id) {
    return getReader(id).getIssueList();
  }

  private Reader getExistsReader(long id) {
    Reader reader = repository.getReaderById(id);
    if (reader == null) {
      throw new NoSuchElementException("Читатеть под id: " + id + " в библиотеке не найден!");
    }
    return reader;
  }

  /**
   * Метод создает и возвращает список книг, которые у читателя на руках
   */
  public List<String> getReaderBooks(Reader reader) {
    List<String> books = new ArrayList<>();
    for (Issue issue : reader.getIssueList()) {
      if (issue.getReturned_at() == null) {
        books.add(bookRepository.getBookById(issue.getBookId()).getName());
      }
    }
    return books;
  }

  public List<Reader> getAllReaders() {
    return repository.getReaders();
  }
}
