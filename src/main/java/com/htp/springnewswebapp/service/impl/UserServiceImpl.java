package com.htp.springnewswebapp.service.impl;

import com.htp.springnewswebapp.dao.UserDaoException;
import com.htp.springnewswebapp.dao.UserDAO;
import com.htp.springnewswebapp.entity.User;
import com.htp.springnewswebapp.entity.UserRepositoryUserDetails;
import com.htp.springnewswebapp.entity.UserStatus;
import com.htp.springnewswebapp.service.ServiceException;
import com.htp.springnewswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
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
		} catch(UserDaoException e) {
			throw new ServiceException(e);
		}
		
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Implement the logic to load the user details from your data source
		// using the provided username parameter.
		// This method should return a UserDetails object containing the user's information.

		System.out.println("loadUserByUsername loadUserByUsername loadUserByUsername");
		User user = userDAO.findByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}

		// Build and return a UserDetails object based on the retrieved User entity.
		return new UserRepositoryUserDetails(user);


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

		} catch(UserDaoException e) {
			throw new ServiceException(e);
		}
	}

}
