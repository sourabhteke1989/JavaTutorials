package com.maxlogic.springboottutorial.projects.loadbalancer;

import com.maxlogic.springboottutorial.projects.loadbalancer.validation.ServerConfigValidator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@ServerConfigValidator
public class Server {
  @NotEmpty String ip;
  @NotNull int weight;
}
