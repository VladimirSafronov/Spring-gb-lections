package ru.safronov.mySpringProject.lection3.application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

  private UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping
  public String listUsers(Model model) {
    model.addAttribute("users", service.getAllUsers());
    return "users";
  }

  @GetMapping(path = "/{id}")
  public String getUser(@PathVariable Long id, Model model) {
    model.addAttribute("user", service.getUserById(id));
    return "userProfile";
  }
}
