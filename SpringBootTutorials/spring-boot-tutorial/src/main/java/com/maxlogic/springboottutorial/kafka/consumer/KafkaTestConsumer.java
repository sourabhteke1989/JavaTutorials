package com.maxlogic.springboottutorial.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

@Slf4j
public class KafkaTestConsumer implements AcknowledgingMessageListener<String, String> {

  @Override
  public void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
    log.info(
        "KafkaTestConsumer [topic: {}] - Consumed message -> {}", record.topic(), record.value());
  }
}
