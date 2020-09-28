package com.maxlogic.my_backend_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxlogic.my_backend_service.entity.User;
import com.maxlogic.my_backend_service.repository.UserDao;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	@GetMapping
	public List<User> getUsers() {
		return this.userDao.findAll();
	}
	
	@PostMapping
	public void addUser(@RequestBody User user) {
		this.userDao.save(user);
	}
	
	@GetMapping("/page/{pageNo}")
	public Page<User> getUsers(@PathVariable int pageNo) {
		return this.userDao.findAll(PageRequest.of(pageNo, 5));
	}

}
