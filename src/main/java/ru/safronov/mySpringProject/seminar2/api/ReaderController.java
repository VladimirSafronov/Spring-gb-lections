package ru.safronov.mySpringProject.seminar2.api;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.safronov.mySpringProject.seminar2.model.Issue;
import ru.safronov.mySpringProject.seminar2.model.Reader;
import ru.safronov.mySpringProject.seminar2.service.ReaderService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reader")
public class ReaderController {

  private final ReaderService service;

  @GetMapping(path = "/{id}")
  public ResponseEntity<Reader> getReaderById(@PathVariable long id) {
    final Reader reader;
    try {
      reader = service.getReader(id);
    } catch (NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(reader, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Reader> createReader(@RequestParam String name) {
    Reader reader = service.createReader(name);
    return new ResponseEntity<>(reader, HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<List<Reader>> deleteReader(@PathVariable long id) {
    final List<Reader> readers;
    try {
      readers = service.deleteReader(id);
    } catch (NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(readers, HttpStatus.OK);
  }

  @GetMapping(path = "/{id}/issue")
  public ResponseEntity<List<Issue>> getReaderIssueList(@PathVariable long id) {
    final List<Issue> issues;
    try {
      issues = service.getIssueList(id);
    } catch (NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(issues, HttpStatus.OK);
  }
}
