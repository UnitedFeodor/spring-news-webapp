package com.htp.springnewswebapp.dao;


import com.htp.springnewswebapp.entity.User;

public interface UserDAO {
	
	User signIn(User user) throws UserDaoException;

	//boolean signOut(int id) throws UserDaoException;
	boolean signUp(User user) throws UserDaoException;

	User findByLogin(String login);
}
