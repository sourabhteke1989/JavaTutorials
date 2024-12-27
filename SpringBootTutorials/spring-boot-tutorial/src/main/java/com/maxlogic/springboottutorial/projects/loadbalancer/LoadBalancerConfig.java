package com.maxlogic.springboottutorial.projects.loadbalancer;

import com.maxlogic.springboottutorial.projects.loadbalancer.validation.LoadBalancerConfigValidator;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
@LoadBalancerConfigValidator
@PropertySource("classpath:loadbalancer.properties")
@ConfigurationProperties(prefix = "maxlogic.loadbalancer.config")
@Data
public class LoadBalancerConfig {

  @NotNull @NotEmpty private List<Server> servers;

  @Bean
  ILoadBalancerContext loadBalancerContext() {
    return new LoadBalancerContext(servers);
  }
}
