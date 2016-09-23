package ua.com.service;

import ua.com.model.User;

public interface UserService {
	public User findByUsername(String username);

}
