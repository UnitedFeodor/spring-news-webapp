package com.htp.springnewswebapp.dao.impl;


import com.htp.springnewswebapp.dao.UserDaoException;
import com.htp.springnewswebapp.dao.UserDAO;
import com.htp.springnewswebapp.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {


	private SessionFactory sessionFactory;
	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User signIn(User user) throws UserDaoException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<User> query = session.createQuery("FROM User WHERE login = :login", User.class);
			query.setParameter("login", user.getLogin());

			User foundUser = query.uniqueResult();
			if (foundUser != null && BCrypt.checkpw(user.getPassword(),foundUser.getPassword())) {
				return foundUser;
			} else {
				return null;
			}
		} catch (HibernateException e) {
			throw new UserDaoException(e);
		}
	}

	@Override
	public boolean signUp(User user) throws UserDaoException {
		try {
			Session session = sessionFactory.getCurrentSession();

			// Check for existing username
			Query<User> query = session.createQuery("from User where login=:login", User.class);
			query.setParameter("login", user.getLogin());
			List<User> users = query.getResultList();
			if (!users.isEmpty()) {
				return false;
			}
			String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hashedPassword);

			session.save(user);
			return true;
		} catch (HibernateException e) {
			throw new UserDaoException(e);
		}
	}


}






