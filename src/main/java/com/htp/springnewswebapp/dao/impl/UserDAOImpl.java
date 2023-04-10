package com.htp.springnewswebapp.dao.impl;


import com.htp.springnewswebapp.dao.DaoException;
import com.htp.springnewswebapp.dao.UserDAO;
import com.htp.springnewswebapp.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {


	private SessionFactory sessionFactory;
	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean signIn(String login, String password) throws DaoException {
		return false;
	}

	@Override
	public boolean signUp(User user) throws DaoException {
		return false;
	}

	@Override
	public String getRole(String login, String password) throws DaoException {
		return null;
	}

	@Override
	public int getIdByLogin(String login) throws DaoException {
		return 0;
	}

	@Override
	public User getUserByLogin(String login) throws DaoException {
		return null;
	}
}






