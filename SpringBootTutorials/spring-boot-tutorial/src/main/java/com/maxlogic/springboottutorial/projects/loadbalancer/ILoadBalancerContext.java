package com.maxlogic.springboottutorial.projects.loadbalancer;

import com.maxlogic.springboottutorial.projects.loadbalancer.strategy.LoadBalancerStrategy;
import java.util.Map;

public interface ILoadBalancerContext {

  Map<String, Integer> getServers();

  void addServer(String serverIp, Integer weigth);

  void removeServer(String serverIp);

  String assignServer();

  void changeStrategy(LoadBalancerStrategy strategy);

  LoadBalancerStrategy getCurrentStrategy();
}
