package com.maxlogic.my_backend_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxlogic.my_backend_service.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
}
