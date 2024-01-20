package ru.safronov.mySpringProject.seminar2.api;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.Data;
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
import ru.safronov.mySpringProject.seminar2.model.Book;
import ru.safronov.mySpringProject.seminar2.service.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/book")
public class BookController {

  private final BookService service;

//  public BookController(BookService bookService) {
//    this.service = bookService;
//  }

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Book> getBookInfo(@PathVariable long id) {
    final Book book;
    try {
      book = service.getBookById(id);
    } catch (NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(book, HttpStatus.OK);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<List<Book>> deleteBook(@PathVariable long id) {
    final List<Book> books;
    try {
      books = service.deleteBook(id);
    } catch (NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
    return new ResponseEntity<>(books, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Book> createBook(@RequestParam String name) {
    Book book = service.addBook(name);
    return new ResponseEntity<>(book, HttpStatus.CREATED);
  }
}
