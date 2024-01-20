package ru.safronov.mySpringProject.seminar2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Repository;
import ru.safronov.mySpringProject.seminar2.model.Issue;

@Repository
public class IssueRepository {

  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public void save(Issue issue) {
    // insert into ....
    issues.add(issue);
  }

  public Issue getIssueById(long id) {
    return issues.stream()
        .filter(it -> Objects.equals(it.getId(), id))
        .findFirst()
        .orElse(null);
  }
}
