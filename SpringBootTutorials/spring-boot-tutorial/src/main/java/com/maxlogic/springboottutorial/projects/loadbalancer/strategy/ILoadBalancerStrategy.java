package com.maxlogic.springboottutorial.projects.loadbalancer.strategy;

import com.maxlogic.springboottutorial.projects.loadbalancer.ILoadBalancerContext;
import com.maxlogic.springboottutorial.projects.loadbalancer.LoadBalancerContext;
import java.util.Random;

public interface ILoadBalancerStrategy {

  Random random = new Random();

  String assignServer(LoadBalancerContext loadBalancerContext);

  LoadBalancerStrategy getStrategyCode();

  default String[] getServersList(ILoadBalancerContext loadBalancerContext) {
    return loadBalancerContext.getServers().keySet().toArray(new String[0]);
  }
}
