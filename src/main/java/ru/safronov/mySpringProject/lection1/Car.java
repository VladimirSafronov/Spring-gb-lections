package ru.safronov.mySpringProject.lection1;

import org.springframework.stereotype.Service;

@Service
public class Car {
//  /**
//   * Внедрение зависимости через поле
//   */
//  @Autowired
  private Engine engine;

  /**
   * Внедрение зависимости через конструктор (предпочтительный способ)
   */
  public Car(Engine engine) {
    this.engine = engine;
    engine.go();
  }

  public void start() {
    engine.go();
  }

//  public Car() {
//  }

//  /**
//   * Внедрение зависимости через сеттер
//   */
//  public void setEngine(Engine engine) {
//    this.engine = engine;
//    engine.go();
//  }
}
