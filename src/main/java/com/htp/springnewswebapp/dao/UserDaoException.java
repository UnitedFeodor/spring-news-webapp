package com.htp.springnewswebapp.dao;

import java.io.Serial;

public class UserDaoException extends Exception {
	@Serial
	private static final long serialVersionUID = 8814453066415187129L;

	public UserDaoException() {
		super();
	}
	
	public UserDaoException(String message) {
		super(message);
	}
	
	public UserDaoException(Exception e) {
		super(e);
	}
	
	public UserDaoException(String message, Exception e) {
		super(message, e);
	}
}
