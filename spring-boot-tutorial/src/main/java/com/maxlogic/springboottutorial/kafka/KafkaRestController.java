package com.maxlogic.springboottutorial.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaRestController {

  private static final String TEST_TOPIC = "test.topic";

  @Autowired KafkaTemplate<String, String> kafkaTemplate;

  @PostMapping(path = "/kafka/produce")
  public String produceMessage(@RequestParam("message") String message) {
    ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(TEST_TOPIC, message);

    future.addCallback(
        new ListenableFutureCallback<SendResult<String, String>>() {
          @Override
          public void onFailure(Throwable ex) {
            log.error("Failure while sending message", ex);
          }

          @Override
          public void onSuccess(SendResult<String, String> result) {
            log.info("Successfully sent message [{}]", result.getProducerRecord().value());
          }
        });

    return "success";
  }

  @KafkaListener(topics = TEST_TOPIC, groupId = "test-1")
  public void receiveMessage(String message) {
    log.info("Received message on test.topic [{}]", message);
  }

  @KafkaListener(
      topics = TEST_TOPIC,
      groupId = "test-2",
      containerFactory = "kafkaListenerContainerFactory")
  public void receiveMessage(
      ConsumerRecord<String, String> payload, Acknowledgment acknowledgment) {
    log.info("Received message with manual acknowledgement on test.topic [{}]", payload.value());
    acknowledgment.acknowledge();
  }
}
