package ru.safronov.mySpringProject.Library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safronov.mySpringProject.Library.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

  List<Issue> findAllByReaderId(Long id);
}
