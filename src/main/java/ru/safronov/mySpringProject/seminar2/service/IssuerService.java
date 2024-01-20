package ru.safronov.mySpringProject.seminar2.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safronov.mySpringProject.seminar2.api.IssueRequest;
import ru.safronov.mySpringProject.seminar2.exceptions.TooManyBooksException;
import ru.safronov.mySpringProject.seminar2.model.Issue;
import ru.safronov.mySpringProject.seminar2.model.Reader;
import ru.safronov.mySpringProject.seminar2.repository.BookRepository;
import ru.safronov.mySpringProject.seminar2.repository.IssueRepository;
import ru.safronov.mySpringProject.seminar2.repository.ReaderRepository;

@Service
@RequiredArgsConstructor
public class IssuerService {

  // спринг это все заинжектит
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
    if (reader.getBooksCount() < reader.getMaxAllowedBooks()) {
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
      throw new NoSuchElementException("Не найдено читателя, с записью о выддаче книги: " + issue);
    }
    reader.getIssueList().remove(issue);
    reader.setBooksCount(reader.getBooksCount() - 1);
    issue.setReturned_at(LocalDateTime.now());
  }

}
