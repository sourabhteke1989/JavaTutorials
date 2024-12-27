package com.maxlogic.springboottutorial.service;

import com.maxlogic.springboottutorial.restcontroller.UserCredentials;

public interface IUserService {
  UserCredentials createUser(UserCredentials userCredentials);

  UserCredentials getUser(Integer id);
}
