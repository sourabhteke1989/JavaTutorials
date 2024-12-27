package com.maxlogic.springboottutorial.projects.loadbalancer.strategy;

import com.maxlogic.springboottutorial.projects.loadbalancer.LoadBalancerContext;

public class RandomStrategy extends AbstractStrategy implements ILoadBalancerStrategy {

  public RandomStrategy(LoadBalancerStrategy strategyCode) {
    super(strategyCode);
  }

  @Override
  public String assignServer(LoadBalancerContext loadBalancerContext) {
    String[] servers = getServersList(loadBalancerContext);
    return servers[random.nextInt(servers.length)];
  }
}
