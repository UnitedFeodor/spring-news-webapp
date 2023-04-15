package com.htp.springnewswebapp.service.impl;

import com.htp.springnewswebapp.dao.DaoException;
import com.htp.springnewswebapp.dao.UserDAO;
import com.htp.springnewswebapp.entity.User;
import com.htp.springnewswebapp.entity.UserDetails;
import com.htp.springnewswebapp.entity.UserStatus;
import com.htp.springnewswebapp.service.ServiceException;
import com.htp.springnewswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	private  UserDAO userDAO;
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional
	@Override
	public User signIn(User user) throws ServiceException {
		try {
			return (userDAO.signIn(user));
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
		
	}

	@Transactional
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

	@Transactional
	@Override
	public boolean signUp(User user)  throws ServiceException {
		try {
			final int DB_ON_REGISTER_USER_STATUS_ID = 2; // 'active'
			UserStatus userStatus = new UserStatus();
			userStatus.setId(DB_ON_REGISTER_USER_STATUS_ID);
			user.setStatus(userStatus);


			return userDAO.signUp(user);

		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

}
