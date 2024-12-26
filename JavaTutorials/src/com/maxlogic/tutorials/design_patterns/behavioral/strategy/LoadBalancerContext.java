package com.maxlogic.tutorials.design_patterns.behavioral.strategy;

public class LoadBalancerContext {

  ILoadBalancerStrategy loadBalancerStrategy;

  LoadBalancerContext(ILoadBalancerStrategy defaultLoadBalancerStrategy){
    loadBalancerStrategy = defaultLoadBalancerStrategy;
  }
}
