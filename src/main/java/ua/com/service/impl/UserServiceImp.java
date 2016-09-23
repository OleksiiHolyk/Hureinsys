package ua.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.Repository.UserRepository;
import ua.com.service.UserService;
import ua.com.model.User;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository repository;

	public User findByUsername(String username){
		return repository.findByUsername(username);
	}
}
