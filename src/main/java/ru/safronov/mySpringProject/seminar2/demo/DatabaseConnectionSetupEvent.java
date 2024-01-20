package ru.safronov.mySpringProject.seminar2.demo;

import org.springframework.context.ApplicationEvent;

public class DatabaseConnectionSetupEvent extends ApplicationEvent {

  public DatabaseConnectionSetupEvent(Object source) {
    super(source);
  }

}
