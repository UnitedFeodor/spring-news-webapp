package com.htp.springnewswebapp.dao;


import com.htp.springnewswebapp.entity.User;

public interface UserDAO {
	
	boolean signIn(String login, String password) throws DaoException;

	//boolean signOut(int id) throws DaoException;
	boolean signUp(User user) throws DaoException;
	String getRole(String login, String password) throws DaoException;

	int getIdByLogin(String login) throws DaoException;
	User getUserByLogin(String login) throws DaoException;


}
