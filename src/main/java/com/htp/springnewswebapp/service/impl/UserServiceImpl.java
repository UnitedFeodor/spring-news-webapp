package com.htp.springnewswebapp.service.impl;

import com.htp.springnewswebapp.dao.UserDaoException;
import com.htp.springnewswebapp.dao.UserDAO;
import com.htp.springnewswebapp.entity.User;
import com.htp.springnewswebapp.entity.UserStatus;
import com.htp.springnewswebapp.service.ServiceException;
import com.htp.springnewswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

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

	public final static class UserRepositoryUserDetails implements UserDetails {

		private final User user;
		private UserRepositoryUserDetails(User user) {
			this.user = user;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return AuthorityUtils.createAuthorityList(user.getRole().getTitle());
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getLogin();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		public User getUser() {
			return this.user;
		}

		private static final long serialVersionUID = 5639683223516504866L;
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
