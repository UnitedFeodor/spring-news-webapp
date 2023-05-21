package com.htp.springnewswebapp.service;


import com.htp.springnewswebapp.entity.User;

public interface UserService {
	
	User signIn(User user) throws ServiceException;
	//boolean signOut(int id) throws ServiceException;
	boolean signUp(User user) throws ServiceException;

}
