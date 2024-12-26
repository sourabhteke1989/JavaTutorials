package com.maxlogic.springboottutorial.kafka;

import static com.maxlogic.springboottutorial.util.AwaitalityUtil.waitAndPoll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.maxlogic.springboottutorial.kafka.config.KafkaConfiguration;
import com.maxlogic.springboottutorial.kafka.handler.TestTopicEventHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@EmbeddedKafka
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(
    locations = {"classpath:application-test.properties", "classpath:kafka-test.properties"})
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = KafkaConfiguration.class)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@TestInstance(Lifecycle.PER_CLASS)
public class TestKafkaRestController {

  @Autowired MockMvc mockMvc;

  @Autowired TestTopicEventHandler testTopicEventHandler;

  @BeforeAll
  void init() {
    testTopicEventHandler.clean();
  }

  @Test
  void testProduceMessage() throws Exception {
    String message = "This is new message !!";
    mockMvc
        .perform(post("/kafka/produce").param("message", message))
        .andExpect(status().isOk())
        .andExpect(content().string("success"));

    waitAndPoll()
        .untilAsserted(() -> assertThat(testTopicEventHandler.getMessage()).isEqualTo(message));
  }
}
