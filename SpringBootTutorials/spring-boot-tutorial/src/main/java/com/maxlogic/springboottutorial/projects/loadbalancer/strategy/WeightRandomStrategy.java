package com.maxlogic.springboottutorial.projects.loadbalancer.strategy;

import com.maxlogic.springboottutorial.projects.loadbalancer.LoadBalancerContext;

public class WeightRandomStrategy extends AbstractStrategy implements ILoadBalancerStrategy {

  public WeightRandomStrategy(LoadBalancerStrategy strategyCode) {
    super(strategyCode);
  }

  @Override
  public String assignServer(LoadBalancerContext loadBalancerContext) {
    return null;
  }
}
