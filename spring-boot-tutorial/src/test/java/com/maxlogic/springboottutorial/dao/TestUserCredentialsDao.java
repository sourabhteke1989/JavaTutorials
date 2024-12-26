package com.maxlogic.springboottutorial.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.maxlogic.springboottutorial.restcontroller.UserCredentials;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class TestUserCredentialsDao {

  @Autowired UserCredentialsDao userCredentialsDao;

  @BeforeAll
  void setup() {
    UserCredentials userCredentials = new UserCredentials(0, "sourabh", "teke");
    userCredentialsDao.save(userCredentials);
    UserCredentials userCredentials1 = new UserCredentials(0, "abc", "pqr");
    userCredentialsDao.save(userCredentials1);
    UserCredentials userCredentials2 = new UserCredentials(0, "xyz", "mnp");
    userCredentialsDao.save(userCredentials2);
  }

  @Test
  void testFindById() {
    Optional<UserCredentials> optUserCred = userCredentialsDao.findById(1);
    assertThat(optUserCred.isPresent()).isTrue();
    assertThat(optUserCred.get().getUserName()).isEqualTo("sourabh");
    assertThat(optUserCred.get().getPassword()).isEqualTo("teke");

    Optional<UserCredentials> optUserCred1 = userCredentialsDao.findById(2);
    assertThat(optUserCred1.isPresent()).isTrue();
    assertThat(optUserCred1.get().getUserName()).isEqualTo("abc");
    assertThat(optUserCred1.get().getPassword()).isEqualTo("pqr");

    Optional<UserCredentials> optUserCred2 = userCredentialsDao.findById(3);
    assertThat(optUserCred2.isPresent()).isTrue();
    assertThat(optUserCred2.get().getUserName()).isEqualTo("xyz");
    assertThat(optUserCred2.get().getPassword()).isEqualTo("mnp");
  }
}
