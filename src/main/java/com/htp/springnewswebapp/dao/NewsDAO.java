package com.htp.springnewswebapp.dao;

import com.htp.springnewswebapp.entity.News;

import java.util.List;



public interface NewsDAO {
	List<News> getList() throws DaoException;
	List<News> getCountNewsStartingFrom(int count, int from) throws DaoException;
	News fetchById(int id) throws DaoException;
	int addNews(News news) throws DaoException;
	void updateNews(News news) throws DaoException;
	void deleteNews(int[] newsIds) throws DaoException;

	int getTotalNewsAmount() throws DaoException;
}
