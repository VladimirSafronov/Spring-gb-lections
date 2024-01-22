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
    return repository.findAll();
  }

  public User getUserById(Long id) {
    return repository.findUser(id);
  }
}
