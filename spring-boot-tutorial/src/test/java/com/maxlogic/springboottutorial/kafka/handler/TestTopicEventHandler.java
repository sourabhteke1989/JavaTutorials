package com.maxlogic.springboottutorial.kafka.handler;

import java.util.LinkedList;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

@Slf4j
public class TestTopicEventHandler implements AcknowledgingMessageListener<String, String> {

  Queue<String> messageQueue = new LinkedList<>();

  @Override
  public void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
    log.info(
        "TestTopicEventHandler [topic: {}] - Consumed message -> {}",
        record.topic(),
        record.value());
    messageQueue.add(record.value());
    acknowledgment.acknowledge();
  }

  public String getMessage() {
    return messageQueue.poll();
  }

  public void clean() {
    messageQueue.clear();
  }
}
