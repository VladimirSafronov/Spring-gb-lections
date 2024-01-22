package ru.safronov.mySpringProject.lection3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

  @GetMapping("/greeting")
  public String greeting(Model model) {
    //в переменную message добавляется значение Привет Thymeleaf!
    model.addAttribute("message", "Привет Thymeleaf!");
    return "greeting";
  }
}
