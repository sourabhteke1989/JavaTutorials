package com.maxlogic.springboottutorial.util;

import static org.awaitility.Awaitility.given;

import java.time.Duration;
import java.util.Objects;
import org.awaitility.core.ConditionFactory;

public class AwaitalityUtil {
  public static ConditionFactory waitAndPoll() {
    return given()
        .ignoreExceptionsMatching(Objects::nonNull)
        .with()
        .await()
        .atMost(Duration.ofSeconds(20))
        .pollInterval(Duration.ofMillis(5))
        .pollDelay(Duration.ofMillis(5));
  }
}
