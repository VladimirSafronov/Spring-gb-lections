package ru.safronov.mySpringProject.seminar2.service;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.safronov.mySpringProject.seminar2.model.Issue;
import ru.safronov.mySpringProject.seminar2.model.Reader;
import ru.safronov.mySpringProject.seminar2.repository.ReaderRepository;

@Service
@RequiredArgsConstructor
public class ReaderService {

  private final ReaderRepository repository;

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
}
