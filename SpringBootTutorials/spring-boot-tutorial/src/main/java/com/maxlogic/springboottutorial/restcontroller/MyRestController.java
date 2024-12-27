package com.maxlogic.springboottutorial.restcontroller;

import com.maxlogic.springboottutorial.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MyRestController {

  @Value("${maxlogic.config.enabled}")
  protected boolean flag;

  @Value("${maxlogic.config.maxproperty}")
  protected String maxproperty;

  @Autowired IUserService userService;

  @GetMapping(path = "/checkProp")
  public String checkPropertiesValue() {
    return "Flag:[" + flag + "], maxProperty:[" + maxproperty + "]";
  }

  @GetMapping(path = "/checkLogging")
  public String checkLogging() {
    log.trace("I am trace log");
    log.debug("I am debug log");
    log.info("I am info log");
    log.warn("I am warn log");
    log.error("I am error log");
    return "Check logs in console and log file !!";
  }

  @PostMapping(path = "/login")
  public String login(@RequestBody MultiValueMap<String, String> paramMap) {
    return "You are logged in with user ["
        + paramMap.getFirst("userName")
        + "], password ["
        + paramMap.getFirst("password")
        + "] !!!";
  }

  @PostMapping(path = "/login2", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String login(UserCredentials userCredentials) {
    return "You are logged in with " + userCredentials.toString();
  }

  @GetMapping(path = "/login3/{userName}")
  public String login(@PathVariable String userName, @RequestParam String password) {
    return "You are logged in with user [" + userName + "], password [" + password + "] !!!";
  }

  @PostMapping(
      path = "/createUserForm",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public UserCredentials createUserForm(UserCredentials userCredentials) {
    return userService.createUser(userCredentials);
  }

  @PostMapping(
      path = "/createUser",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public UserCredentials createUser(@RequestBody UserCredentials userCredentials) {
    return userService.createUser(userCredentials);
  }

  @GetMapping(
      path = "/user",
      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public UserCredentials getUser(@RequestParam Integer id) {
    return userService.getUser(id);
  }
}
