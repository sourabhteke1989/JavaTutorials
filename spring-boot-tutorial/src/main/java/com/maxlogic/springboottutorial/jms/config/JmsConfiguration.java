package com.maxlogic.springboottutorial.jms.config;

import com.maxlogic.springboottutorial.jms.activemq.ActiveMQBrokerFactory;
import com.maxlogic.springboottutorial.jms.activemq.ActiveMqVersion;
import com.maxlogic.springboottutorial.jms.activemq.JmsBroker;
import javax.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("jms.properties")
@ConditionalOnProperty(prefix = "jms", name = "enabled")
public class JmsConfiguration {

  @Value("${jms.vendor}")
  public MqVendor jmsVendor;

  @Value("${jms.url}")
  public String jmsUrl;

  @Value("${jms.user}")
  public String jmsUser;

  @Value("${jms.password}")
  public String jmsPassword;

  @Value("${jms.activemq.version}")
  public ActiveMqVersion jmsActiveMqVersion;

  @Value("${jms.activemq.in-memory}")
  public boolean jmsInMemory;

  @Value("${jms.queue-manager}")
  public String jmsQueueManager;

  @Value("${jms.channel}")
  public String jmsChannel;

  @Value("${jms.transport-type}")
  public String jmsTransportType;

  @Bean
  @Primary
  public ConnectionFactory jmsConnectionFactory() throws Exception {
    switch (jmsVendor) {
      case ACTIVEMQ:
        {
          return activeMQConnectionFactory();
        }
      case IBMMQ:
        {
          return ibmMqConnectionFactory();
        }
        /*case JNDI:
        return jndiConnectionFactory();*/
      default:
        throw new Exception("not supported jms vendor type");
    }
  }

  private ConnectionFactory ibmMqConnectionFactory() {
    return null;
  }

  private ConnectionFactory activeMQConnectionFactory() throws Exception {
    JmsBroker broker = ActiveMQBrokerFactory.createActiveMqSpecificVersion(jmsActiveMqVersion);
    if (jmsInMemory) {
      return broker.startEmbeddedBroker(jmsUrl);
    }
    return broker.createConnectionFactory(jmsUrl, jmsUser, jmsPassword);
  }

  enum MqVendor {
    ACTIVEMQ,
    IBMMQ
  }
}
