package com.maxlogic.springboottutorial.projects.loadbalancer.strategy;

import com.maxlogic.springboottutorial.projects.loadbalancer.LoadBalancerContext;

public class WeightRoundRobinStrategy extends AbstractStrategy implements ILoadBalancerStrategy {

  public WeightRoundRobinStrategy(LoadBalancerStrategy strategyCode) {
    super(strategyCode);
  }

  @Override
  public String assignServer(LoadBalancerContext loadBalancerContext) {
    return null;
  }
}
