package com.maxlogic.springboottutorial.projects.loadbalancer;

import com.maxlogic.springboottutorial.projects.loadbalancer.strategy.ILoadBalancerStrategy;
import com.maxlogic.springboottutorial.projects.loadbalancer.strategy.LoadBalancerStrategy;
import com.maxlogic.springboottutorial.projects.loadbalancer.strategy.LoadBalancerStrategyFactory;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoadBalancerContext implements ILoadBalancerContext {
  Map<String, Integer> servers = new ConcurrentHashMap<>();
  ILoadBalancerStrategy loadBalancerStrategy;

  LoadBalancerContext(List<Server> serversList) {
    serversList.stream().forEach(server -> servers.put(server.getIp(), server.getWeight()));
    loadBalancerStrategy =
        LoadBalancerStrategyFactory.getStrategyInstance(LoadBalancerStrategy.ROUND_ROBIN);
  }

  public Map<String, Integer> getServers() {
    return servers;
  }

  public String assignServer() {
    return loadBalancerStrategy.assignServer(this);
  }

  @Override
  public void changeStrategy(LoadBalancerStrategy strategy) {
    this.loadBalancerStrategy = LoadBalancerStrategyFactory.getStrategyInstance(strategy);
  }

  @Override
  public LoadBalancerStrategy getCurrentStrategy() {
    return this.loadBalancerStrategy.getStrategyCode();
  }

  public void addServer(String serverIp, Integer weight) {
    servers.put(serverIp, weight);
  }

  @Override
  public void removeServer(String serverIp) {
    servers.remove(serverIp);
  }
}
