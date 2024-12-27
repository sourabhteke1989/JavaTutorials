package com.maxlogic.springboottutorial.kafka.config;

import com.maxlogic.springboottutorial.kafka.handler.TestTopicEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;

@Configuration
@PropertySource("classpath:kafka-test.properties")
@Slf4j
public class KafkaTestConfiguration {

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
  public TestTopicEventHandler testTopicEventsHandler() {
    return new TestTopicEventHandler();
  }

  @Bean
  public ConcurrentMessageListenerContainer<String, String> concurrentMessageListenerContainer(
      ConsumerFactory<String, String> consumerFactory) {
    ConcurrentMessageListenerContainer<String, String> container =
        kafkaListenerContainerFactory(consumerFactory).createContainer("test.topic");
    container.setupMessageListener(testTopicEventsHandler());
    container.start();
    return container;
  }
}
