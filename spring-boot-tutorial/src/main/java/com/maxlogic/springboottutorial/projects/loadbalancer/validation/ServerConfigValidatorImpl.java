package com.maxlogic.springboottutorial.projects.loadbalancer.validation;

import com.maxlogic.springboottutorial.projects.loadbalancer.Server;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerConfigValidatorImpl
    implements ConstraintValidator<ServerConfigValidator, Server> {

  @Override
  public boolean isValid(Server server, ConstraintValidatorContext constraintValidatorContext) {
    log.info("Validating server config");
    return true;
  }
}
