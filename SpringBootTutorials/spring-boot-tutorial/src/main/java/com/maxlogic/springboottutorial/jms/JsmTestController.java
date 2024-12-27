package com.maxlogic.springboottutorial.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jms")
@Slf4j
public class JsmTestController {

  public static final String TEST_QUEUE = "test-queue";

  @Autowired private JmsTemplate jmsTemplate;

  @PostMapping("/send")
  public String sendMessage(@RequestBody String message) {
    jmsTemplate.convertAndSend(TEST_QUEUE, message);
    return "success";
  }

  @JmsListener(destination = TEST_QUEUE)
  public void receiveMessage(String message) {
    log.info("Received message in test-queue :[{}]", message);
  }
}
