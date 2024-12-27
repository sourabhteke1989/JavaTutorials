package com.maxlogic.springboottutorial.projects.loadbalancer.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Documented
@Constraint(validatedBy = LoadBalancerConfigValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ReportAsSingleViolation
public @interface LoadBalancerConfigValidator {

  String message() default "Loadbalancer configuration issue";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
