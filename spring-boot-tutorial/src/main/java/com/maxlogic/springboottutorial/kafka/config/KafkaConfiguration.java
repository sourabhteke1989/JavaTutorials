package com.maxlogic.springboottutorial.kafka.config;

import com.maxlogic.springboottutorial.kafka.consumer.KafkaTestConsumer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

@Configuration
@PropertySource("classpath:kafka.properties")
@ConditionalOnProperty(prefix = "kafka", name = "enabled")
public class KafkaConfiguration {

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
      kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory);
    factory.setConcurrency(1);
    factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
    return factory;
  }

  @Bean
  public KafkaTestConsumer kafkaTestConsumer() {
    return new KafkaTestConsumer();
  }

  @Bean
  public ConcurrentMessageListenerContainer<String, String> concurrentMessageListenerContainer(
      ConsumerFactory<String, String> consumerFactory) {
    ConcurrentMessageListenerContainer<String, String> container =
        kafkaListenerContainerFactory(consumerFactory).createContainer("test.topic");
    container.setupMessageListener(kafkaTestConsumer());
    container.start();
    return container;
  }
}
