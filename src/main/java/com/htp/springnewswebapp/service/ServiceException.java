package com.htp.springnewswebapp.service;

public class ServiceException extends Exception{

	public ServiceException(String e) {
		super(e);
	}
	
	public ServiceException(Exception e) {
		super(e);
	}
}
