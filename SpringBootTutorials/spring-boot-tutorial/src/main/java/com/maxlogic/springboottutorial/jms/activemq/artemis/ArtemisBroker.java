package com.maxlogic.springboottutorial.jms.activemq.artemis;

import com.maxlogic.springboottutorial.jms.activemq.JmsBroker;
import javax.jms.ConnectionFactory;
import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.apache.activemq.artemis.core.settings.impl.AddressSettings;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ArtemisBroker implements JmsBroker {

  @Override
  public ConnectionFactory startEmbeddedBroker(String url) throws Exception {
    EmbeddedActiveMQ broker = new EmbeddedActiveMQ();
    Configuration configuration =
        new ConfigurationImpl()
            .setPersistenceEnabled(false)
            .setSecurityEnabled(false)
            .addAcceptorConfiguration("connector", url);

    configuration.addAddressesSetting(
        "#",
        new AddressSettings()
            .setMaxDeliveryAttempts(1)
            .setAutoDeleteAddresses(false)
            .setAutoDeleteQueues(false));
    broker.setConfiguration(configuration);
    broker.start();
    return new ActiveMQConnectionFactory(url);
  }

  @Override
  public ConnectionFactory createConnectionFactory(String url, String user, String password) {
    return new ActiveMQConnectionFactory(url, user, password);
  }
}
