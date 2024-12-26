package com.maxlogic.springboottutorial.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
  @GetMapping(path = "/helloworld")
  public String helloWorld() {
    return "Hi there, Hello World!!";
  }
}
