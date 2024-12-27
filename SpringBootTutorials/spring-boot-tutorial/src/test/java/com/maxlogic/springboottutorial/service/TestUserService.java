package com.maxlogic.springboottutorial.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.maxlogic.springboottutorial.dao.UserCredentialsDao;
import com.maxlogic.springboottutorial.restcontroller.UserCredentials;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@TestInstance(Lifecycle.PER_CLASS)
class TestUserService {

  @InjectMocks UserService userService;

  @Mock UserCredentialsDao userCredentialsDao;

  @BeforeAll
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateUser() {
    when(userCredentialsDao.save(Mockito.any(UserCredentials.class)))
        .thenReturn(new UserCredentials(1, "abc", "pqr"));
    UserCredentials input = new UserCredentials(0, "abc", "abc");
    UserCredentials userCredentials = userService.createUser(input);
    verify(userCredentialsDao, times(1)).save(input);
    assertThat(userCredentials.getId()).isEqualTo(1);
    assertThat(userCredentials.getUserName()).isEqualTo("abc");
    assertThat(userCredentials.getPassword()).isEqualTo("pqr");
  }

  @Test
  void testGetUser() {
    when((userCredentialsDao.findById(24)))
        .thenReturn(Optional.of(new UserCredentials(24, "sourabh", "teke")));
    UserCredentials userCredentials = userService.getUser(24);
    verify(userCredentialsDao, times(1)).findById(24);
    assertThat(userCredentials.getId()).isEqualTo(24);
    assertThat(userCredentials.getUserName()).isEqualTo("sourabh");
    assertThat(userCredentials.getPassword()).isEqualTo("teke");
  }
}
