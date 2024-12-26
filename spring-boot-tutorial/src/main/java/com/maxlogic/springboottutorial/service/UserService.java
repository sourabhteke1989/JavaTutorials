package com.maxlogic.springboottutorial.service;

import com.maxlogic.springboottutorial.dao.UserCredentialsDao;
import com.maxlogic.springboottutorial.restcontroller.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired UserCredentialsDao userCredentialsDao;

  public UserCredentials createUser(UserCredentials userCredentials) {
    return userCredentialsDao.save(userCredentials);
  }

  @Override
  public UserCredentials getUser(Integer id) {
    return userCredentialsDao.findById(id).get();
  }
}
