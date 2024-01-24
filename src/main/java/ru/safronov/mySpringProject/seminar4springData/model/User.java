package ru.safronov.mySpringProject.seminar4springData.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

}
