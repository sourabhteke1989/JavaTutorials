package com.maxlogic.springboottutorial.projects.loadbalancer.validation;

import com.maxlogic.springboottutorial.projects.loadbalancer.LoadBalancerConfig;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadBalancerConfigValidatorImpl
    implements ConstraintValidator<LoadBalancerConfigValidator, LoadBalancerConfig> {

  @Override
  public boolean isValid(
      LoadBalancerConfig loadBalancerConfig,
      ConstraintValidatorContext constraintValidatorContext) {
    log.info("Validating loadbalancer config");
    return true;
  }
}
