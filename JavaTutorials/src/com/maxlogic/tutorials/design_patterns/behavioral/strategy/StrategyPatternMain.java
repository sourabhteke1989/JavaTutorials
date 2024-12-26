package com.maxlogic.tutorials.design_patterns.behavioral.strategy;

public class StrategyPatternMain {

  /**
   *  Strategy Pattern (Policy Pattern): Define a family of algorithms, encapsulate each one, and make them interchangeable.
   *                      Strategy lets the algorithm vary independently from clients that use it.
   *
   *  Entities :
   *    Strategy - common interface for all strategies, declares algorithm methods.
   *    ConcreteStrategy - implements algorithm using Strategy interface.
   *    Context - is configured with ConcreteStrategy object,
   *              has ConcreteStrategy object as instance property,
   *              may define interface that lets Strategy access its data.
   *
   *  Example : Load balancer different strategy implementations.
   *    Strategy - ILoadBalancerStrategy interface, declares assignServer() algorithm method.
   *    ConcreteStrategy - RandomStrategy, RoundRobinStrategy, WeightRandomStrategy, WeightRoundRobinStrategy classes
   *    Context - LoadBalancerContext class, maintains reference to strategy object.
   *              Also contains servers related all information, and define interface for strategy to access servers related data.
   *
   *
   */

  public static void main(String[] args) {

  }

}
