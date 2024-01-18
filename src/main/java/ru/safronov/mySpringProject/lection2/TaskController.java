package ru.safronov.mySpringProject.lection2;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/task")
public class TaskController {

  private final TaskService service;

  public TaskController(TaskService service) {
    this.service = service;
  }

  @GetMapping
  public List<Task> getAllTasks() {
    return service.getAllTasks();
  }

  @GetMapping(path = "/{id}")
  public Task getById(@PathVariable UUID id) {
    return service.getTask(id);
  }

  @PostMapping()
  public Task addTask(@RequestBody Task task) {
    return service.addTask(task);
  }

  @PutMapping(path = "/{id}")
  public Task updateTask(@PathVariable UUID id, @RequestBody Task task) {
    return service.updateTask(id, task);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteTask(@PathVariable UUID id) {
    service.removeTask(id);
  }
}
