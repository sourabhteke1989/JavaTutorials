package com.maxlogic.springboottutorial.dao;

import com.maxlogic.springboottutorial.restcontroller.UserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsDao extends CrudRepository<UserCredentials, Integer> {

}
