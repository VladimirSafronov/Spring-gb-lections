package ru.safronov.mySpringProject.lection1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
  Car car;

  public CarController(Car car) {
    this.car = car;
  }

  @GetMapping("/car")
  public String carStart() {
    car.start();
    return "Авто запущен";
  }
}
