package ru.safronov.mySpringProject.lection3.application;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private Map<Long, User> users = new ConcurrentHashMap<>();
  private AtomicLong counter = new AtomicLong();

  @PostConstruct
  public void initUsers() {
    users.put(counter.incrementAndGet(), new User(counter.get(), "Boris", "borsi@ya.ru"));
    users.put(counter.incrementAndGet(), new User(counter.get(), "Andrey", "andy@ya.ru"));
    users.put(counter.incrementAndGet(), new User(counter.get(), "Bobby", "bob@ya.ru"));
  }

  public List<User> findAll() {
    return new ArrayList<>(users.values());
  }

  public User findUser(Long id) {
    return users.get(id);
  }

  public User saveUser(User user) {
    if (user.getId() == null) {
      user.setId(counter.incrementAndGet());
    }
    users.put(user.getId(), user);
    return user;
  }

}
