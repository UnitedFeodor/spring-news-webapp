package com.htp.springnewswebapp.dao;


import com.htp.springnewswebapp.entity.User;

public interface UserDAO {
	
	User signIn(User user) throws DaoException;

	//boolean signOut(int id) throws DaoException;
	boolean signUp(User user) throws DaoException;


}
