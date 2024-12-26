package com.maxlogic.springboottutorial.projects.loadbalancer.strategy;

import com.maxlogic.springboottutorial.projects.loadbalancer.LoadBalancerContext;

public class RoundRobinStrategy extends AbstractStrategy implements ILoadBalancerStrategy {

  private Integer position = 0;

  public RoundRobinStrategy(LoadBalancerStrategy strategyCode) {
    super(strategyCode);
  }

  @Override
  public String assignServer(LoadBalancerContext loadBalancerContext) {
    String[] servers = getServersList(loadBalancerContext);
    synchronized (position) {
      if (position == servers.length) {
        position = 0;
      }
      return servers[position++];
    }
  }
}
