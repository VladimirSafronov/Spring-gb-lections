package ru.safronov.mySpringProject.Library.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.safronov.mySpringProject.Library.api.IssueRequest;
import ru.safronov.mySpringProject.Library.exceptions.TooManyBooksException;
import ru.safronov.mySpringProject.Library.model.Issue;
import ru.safronov.mySpringProject.Library.model.Reader;
import ru.safronov.mySpringProject.Library.repository.BookRepository;
import ru.safronov.mySpringProject.Library.repository.IssueRepository;
import ru.safronov.mySpringProject.Library.repository.ReaderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssuerService {

  /**
   * Максимально возможное количество книг на руках (1 если ничего не задано)
   */
  @Value("${application.issue.max-allowed-books:1}")
  private int maxAllowedBooks;
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException(
          "Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException(
          "Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }

    Issue issue;
    Reader reader = readerRepository.getReaderById(request.getReaderId());
    if (reader.getBooksCount() < maxAllowedBooks) {
      issue = new Issue(request.getBookId(), request.getReaderId());
      issueRepository.save(issue);
      reader.getIssueList().add(issue);
      reader.setBooksCount(reader.getBooksCount() + 1);
    } else {
      throw new TooManyBooksException(
          "У читателя с идетнификатором " + reader.getId() + " на руках "
              + "максимальное количество книг: " + reader.getBooksCount());
    }
    return issue;
  }

  public Issue getIssue(long id) {
    Issue issue = issueRepository.getIssueById(id);
    if (issue == null) {
      throw new NoSuchElementException(
          "Не найдено записи в книге выдачи под идентификатором " + id);
    }
    return issue;
  }

  public void returnBook(Issue issue) {
    Reader reader = readerRepository.getReaderByIssue(issue);
    if (reader == null) {
      throw new NoSuchElementException("Не найдено читателя, с записью о выдаче книги: " + issue);
    }
    reader.getIssueList().remove(issue);
    reader.setBooksCount(reader.getBooksCount() - 1);
    issue.setReturned_at(LocalDateTime.now());
  }

  /**
   * Метод генерирует и возвращает список списков из (Название книги, Имя читателя, Дата заказа,
   * Дата возврата)
   */
  public List<List<String>> getIssuesData() {
    List<List<String>> allIssuesData = new ArrayList<>();
    for (Issue issue : issueRepository.getIssues()) {
      String bookName = bookRepository.getBookById(issue.getBookId()).getName();
      String readerName = readerRepository.getReaderById(issue.getReaderId()).getName();
      String issuedAt = issueRepository.getIssueById(issue.getId()).getIssued_at().toString();
      String returnedAt =
          issueRepository.getIssueById(issue.getId()).getReturned_at() == null ? "" :
              issueRepository.getIssueById(issue.getId()).getReturned_at().toString();
      allIssuesData.add(List.of(bookName, readerName, issuedAt, returnedAt));
      log.info(allIssuesData.toString());
    }
    return allIssuesData;
  }


}
