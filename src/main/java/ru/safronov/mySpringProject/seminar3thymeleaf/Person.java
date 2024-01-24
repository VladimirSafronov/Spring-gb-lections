package ru.safronov.mySpringProject.seminar3thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person {

  private String firstName;
  private String lastName;
  private int age;
  private String company;

}
