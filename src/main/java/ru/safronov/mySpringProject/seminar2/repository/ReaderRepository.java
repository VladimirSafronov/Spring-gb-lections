package ru.safronov.mySpringProject.seminar2.repository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;
import ru.safronov.mySpringProject.seminar2.model.Issue;
import ru.safronov.mySpringProject.seminar2.model.Reader;

@Repository
public class ReaderRepository {

  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  /**
   * Аннотация @PostConstruct позволяет произвести какие-то действия после создания бина
   */
  @PostConstruct
  public void generateData() {
    readers.addAll(List.of(
        new ru.safronov.mySpringProject.seminar2.model.Reader("Игорь")
    ));
  }

  public Reader getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
        .findFirst()
        .orElse(null);
  }

  public Reader getReaderByIssue(Issue issue) {
    for (Reader reader : readers) {
      for (Issue i : reader.getIssueList()) {
        if (i.equals(issue)) {
          return reader;
        }
      }
    }
    return null;
  }

  public Reader addReader(String name) {
    Reader reader = new Reader(name);
    readers.add(reader);
    return reader;
  }

  public List<Reader> deleteReader(Reader reader) {
    readers.remove(reader);
    return readers;
  }

  public List<Issue> getIssueList(long id) {
    return getReaderById(id).getIssueList();
  }
}
