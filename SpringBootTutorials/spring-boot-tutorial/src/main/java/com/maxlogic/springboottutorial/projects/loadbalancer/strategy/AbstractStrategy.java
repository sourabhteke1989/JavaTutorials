package com.maxlogic.springboottutorial.projects.loadbalancer.strategy;

public abstract class AbstractStrategy implements ILoadBalancerStrategy {

  private final LoadBalancerStrategy strategyCode;

  public AbstractStrategy(LoadBalancerStrategy strategyCode) {
    this.strategyCode = strategyCode;
  }

  @Override
  public LoadBalancerStrategy getStrategyCode() {
    return strategyCode;
  }
}
