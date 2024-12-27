package com.maxlogic.springboottutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.maxlogic.springboottutorial.config"})
public class SpringBootTutorialApplication {

  public static void main(String[] args) {
    System.out.println("Starting Application");
    SpringApplication.run(SpringBootTutorialApplication.class, args);
  }
}
