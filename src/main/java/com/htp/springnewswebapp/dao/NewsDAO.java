package com.htp.springnewswebapp.dao;

import com.htp.springnewswebapp.entity.News;

import java.util.List;



public interface NewsDAO {
	List<News> getAllNews() throws UserDaoException;
	List<News> getCountNewsStartingFrom(int count, int from) throws UserDaoException;
	News findById(int id) throws UserDaoException;
	Integer addNews(News news) throws UserDaoException;
	void updateNews(News news) throws UserDaoException;
	void deleteNews(int[] newsIds) throws UserDaoException;
	int getTotalNewsAmount() throws UserDaoException;
}
