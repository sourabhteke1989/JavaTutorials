package com.maxlogic.springboottutorial.projects.loadbalancer;

import com.maxlogic.springboottutorial.projects.loadbalancer.strategy.LoadBalancerStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loadbalancer")
public class LoadBalancerController {

  @Autowired ILoadBalancerContext loadBalancerContext;

  @PostMapping
  public ResponseEntity<String> addServer(String serverIp, Integer weight) {
    loadBalancerContext.addServer(serverIp, weight);
    return ResponseEntity.ok("success");
  }

  @DeleteMapping(path = "/{serverIp}")
  public ResponseEntity<String> removeServer(@PathVariable String serverIp) {
    loadBalancerContext.removeServer(serverIp);
    return ResponseEntity.ok("success");
  }

  @GetMapping
  public ResponseEntity<String> assignServer() {
    return ResponseEntity.ok(loadBalancerContext.assignServer());
  }

  @PostMapping(path = "/strategy/{strategyCode}")
  public ResponseEntity<String> changeStrategy(@PathVariable LoadBalancerStrategy strategyCode) {
    loadBalancerContext.changeStrategy(strategyCode);
    return ResponseEntity.ok("success");
  }

  @GetMapping(path = "/strategy")
  public ResponseEntity<String> getCurrentStrategy() {
    return ResponseEntity.ok(loadBalancerContext.getCurrentStrategy().toString());
  }
}
