package ru.safronov.mySpringProject.lection3.application;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class User {

  private Long id;
  private String name;
  private String email;

  public User(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }
}
