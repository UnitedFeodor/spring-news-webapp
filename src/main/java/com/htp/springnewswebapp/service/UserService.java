package com.htp.springnewswebapp.service;


import com.htp.springnewswebapp.entity.User;

public interface UserService {
	
	User signIn(String login, String password) throws ServiceException;
	//boolean signOut(int id) throws ServiceException;
	boolean signUp(User user) throws ServiceException;

	int getIdByLogin(String login) throws ServiceException;
}
