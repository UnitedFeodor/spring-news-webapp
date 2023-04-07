package com.htp.springnewswebapp.dao.impl;


import com.htp.springnewswebapp.dao.DaoException;
import com.htp.springnewswebapp.dao.UserDAO;
import com.htp.springnewswebapp.entity.User;

public class UserDAOImpl implements UserDAO {

	private static final String DB_ID = "id";
	private static final String DB_LOGIN = "login";
	private static final String DB_PASSWORD = "password";

	private static final String DB_ROLE_ID = "roles_id";
	private static final String DB_STATUS_ID = "status_id";

	private static final String DB_ROLES_TITLE = "title";

	private static final int DB_ON_REGISTER_USER_STATUS_ID = 2; // 'active'

	private static final String Q_GET_ALL_USERS = "SELECT * FROM users";
	//private static final String Q_GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";
	private static final String Q_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

	private static final String Q_GET_ROLE_ID_BY_TITLE = "SELECT id FROM roles WHERE title = ?";

	// UPDATE users SET status_id = 1 WHERE login = 't';
	private static final String Q_UPDATE_USER_STATUS_ID_BY_LOGIN = "UPDATE users SET status_id = ? WHERE login = ?";
	private static final String Q_UPDATE_USER_STATUS_ID_BY_USER_ID = "UPDATE users SET status_id = ? WHERE id = ?";
	private static final String Q_GET_ROLE_ID_BY_TITLE_AND_STATUS_ID_BY_NAME = "SELECT roles.id,user_status.id FROM roles LEFT JOIN user_status ON user_status.status_name = ? WHERE roles.title = ? ";

	private static final String Q_GET_USER_AND_ROLE_BY_LOGIN = "SELECT users.*, roles.title FROM users LEFT JOIN roles ON users.roles_id = roles.id WHERE login = ?";
	private static final String Q_GET_ID_BY_LOGIN = "SELECT id FROM users WHERE login = ?";
	private static final String Q_GET_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";
	private static final String Q_INSERT_USER = "INSERT INTO users (login,password,roles_id,status_id) VALUES (?,?,?,?)";

	private static final String Q_INSERT_USER_DETAILS = "INSERT INTO user_details (users_id,name,surname,birthday) VALUES (?,?,?,?)";


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






