package com.BookStore.app.service;

import java.util.List;

import com.BookStore.app.exception.UserException;
import com.BookStore.app.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public List<User> findAllUsers();

}
