package ru.safronov.mySpringProject.lection3.application;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  public List<User> getAllUsers() {
    repository.save(new User(null, "Boris", "borsi@ya.ru"));
    repository.save(new User(null, "Andrey", "andy@ya.ru"));
    repository.save(new User(null, "Bobby", "bob@ya.ru"));
    return repository.findAll();
  }

  public User getUserById(Long id) {
    return repository.findById(id).get();
  }
}
