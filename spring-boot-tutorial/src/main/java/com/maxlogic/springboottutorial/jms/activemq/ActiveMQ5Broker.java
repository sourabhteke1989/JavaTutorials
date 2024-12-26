package com.maxlogic.springboottutorial.jms.activemq;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

public class ActiveMQ5Broker implements JmsBroker {

  @Override
  public ConnectionFactory startEmbeddedBroker(String url) throws Exception {
    BrokerService broker = new BrokerService();
    broker.addConnector(url);
    broker.start();
    return new ActiveMQConnectionFactory(url);
  }

  @Override
  public ConnectionFactory createConnectionFactory(String url, String user, String password) {
    return new ActiveMQConnectionFactory(user, password, url);
  }
}
