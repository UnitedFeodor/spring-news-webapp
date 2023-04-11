package com.htp.springnewswebapp.service.impl;

import com.htp.springnewswebapp.dao.DaoException;
import com.htp.springnewswebapp.dao.UserDAO;
import com.htp.springnewswebapp.entity.User;
import com.htp.springnewswebapp.service.ServiceException;
import com.htp.springnewswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private  UserDAO userDAO;
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User signIn(User user) throws ServiceException {
		try {
			return (userDAO.signIn(user));
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
		
	}


	@Override
	public int getIdByLogin(String login) throws ServiceException {
		// TODO change
//		User userToValidate = new User();
//		userToValidate.setLogin(login);
//
//		try {
//			return userDAO.getIdByLogin(login);
//		} catch (DaoException e) {
//			throw new ServiceException(e);
//		}
		return 0;
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
