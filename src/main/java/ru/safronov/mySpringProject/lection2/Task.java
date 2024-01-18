package ru.safronov.mySpringProject.lection2;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class Task {

  public enum Status {
    TO_DO,
    IN_PROGRESS,
    DONE
  }

  private UUID id;
  private String name;
  private String description;
  private Status status;
  private LocalDateTime completionTime;

  public Task(String name, String description) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.status = Status.TO_DO;
  }
}
