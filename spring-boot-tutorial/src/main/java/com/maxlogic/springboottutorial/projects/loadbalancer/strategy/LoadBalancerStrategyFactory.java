package com.maxlogic.springboottutorial.projects.loadbalancer.strategy;

public class LoadBalancerStrategyFactory {
  public static ILoadBalancerStrategy getStrategyInstance(LoadBalancerStrategy strategy) {
    switch (strategy) {
      case RANDOM:
        return new RandomStrategy(strategy);
      case ROUND_ROBIN:
        return new RoundRobinStrategy(strategy);
      case WEIGHT_RANDOM:
        return new WeightRandomStrategy(strategy);
      case WEIGHT_ROUND_ROBIN:
        return new WeightRoundRobinStrategy(strategy);
      default:
        return new RoundRobinStrategy(strategy);
    }
  }
}
