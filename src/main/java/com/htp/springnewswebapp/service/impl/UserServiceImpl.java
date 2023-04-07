package com.htp.springnewswebapp.service.impl;

import com.htp.springnewswebapp.dao.DaoException;
import com.htp.springnewswebapp.dao.UserDAO;
import com.htp.springnewswebapp.entity.User;
import com.htp.springnewswebapp.service.ServiceException;
import com.htp.springnewswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_USER = "user";
	public static final String ROLE_GUEST = "guest";

	@Autowired
	private final UserDAO userDAO;

	@Override
	public User signIn(String login, String password) throws ServiceException {
		

		User userToValidate = new User();
		userToValidate.setLogin(login);
		userToValidate.setPassword(password);

		try {
			if(userDAO.signIn(login, password)) {
				return userDAO.getUserByLogin(login);
			} else {
				return null;
			}
			
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
		
	}


	@Override
	public int getIdByLogin(String login) throws ServiceException {
		User userToValidate = new User();
		userToValidate.setLogin(login);

		try {
			return userDAO.getIdByLogin(login);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean signUp(User user)  throws ServiceException {
		try {
			return userDAO.signUp(user);

		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

}
