package com.maxlogic.springboottutorial.jms.activemq;

import com.maxlogic.springboottutorial.jms.activemq.artemis.ArtemisBroker;

public class ActiveMQBrokerFactory {

  public static JmsBroker createActiveMqSpecificVersion(ActiveMqVersion version) {
    if (version == null) {
      return new ArtemisBroker();
    }
    switch (version) {
      case ACTIVEMQ_5:
        return new ActiveMQ5Broker();
      case ARTEMIS:
        return new ArtemisBroker();
      default:
        return new ArtemisBroker();
    }
  }
}
