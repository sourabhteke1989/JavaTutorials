package com.maxlogic.springboottutorial.jms.activemq;

import javax.jms.ConnectionFactory;

public interface JmsBroker {
  ConnectionFactory startEmbeddedBroker(String url) throws Exception;

  ConnectionFactory createConnectionFactory(String url, String user, String password);
}
