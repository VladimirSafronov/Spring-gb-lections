package ru.safronov.mySpringProject.Library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.safronov.mySpringProject.Library.model.Reader;
import ru.safronov.mySpringProject.Library.service.BookService;
import ru.safronov.mySpringProject.Library.service.IssuerService;
import ru.safronov.mySpringProject.Library.service.ReaderService;

@Controller
@RequestMapping("/ui")
public class HtmlController {

  private final BookService bookService;
  private final ReaderService readerService;
  private final IssuerService issuerService;

  public HtmlController(BookService bookService, ReaderService readerService, IssuerService issuerService) {
    this.bookService = bookService;
    this.readerService = readerService;
    this.issuerService = issuerService;
  }

  @GetMapping("/books")
  public String getAvailableBooks(Model model) {
    model.addAttribute("books", bookService.getAllBooks());
    return "availableBooks";
  }

  @GetMapping("/readers")
  public String getReaders(Model model) {
    model.addAttribute("readers", readerService.getAllReaders());
    return "readers";
  }

  @GetMapping("/issues")
  public String getIssues(Model model) {

    model.addAttribute("issues", issuerService.getIssuesData());
    return "issues";
  }

  @GetMapping("/reader/{id}")
  public String getReaderInfo(@PathVariable Long id, Model model) {
    Reader reader = readerService.getReader(id);
    model.addAttribute("reader", reader);
    model.addAttribute("readerBooks", readerService.getReaderBooks(reader));
    return "readerInfo";
  }
}
