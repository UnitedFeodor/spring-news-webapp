package com.htp.springnewswebapp.dao.impl;

import com.htp.springnewswebapp.dao.DaoException;
import com.htp.springnewswebapp.entity.News;


import java.util.List;

public class NewsDAOImpl implements com.htp.springnewswebapp.dao.NewsDAO {

	private static final String DB_ID = "id";
	private static final String DB_TITLE = "title";
	private static final String DB_DATE = "date_added";
	private static final String DB_BRIEF = "brief";
	private static final String DB_CONTENT = "content";
	
	
	private static final String Q_INSERT_NEWS = "INSERT INTO news (title, date_added, brief, content, authors_user_id, news_status_id) VALUES (?,?,?,?,?,?)";

	private static final String Q_DELETE_IDS = "DELETE FROM news WHERE id = ?";
	private static final String Q_UPDATE_NEWS_BY_ID = "UPDATE news SET title = ?, brief = ?, content = ?,date_added = ? WHERE id = ?";
	private static final String Q_GET_ALL_NEWS = "SELECT * FROM news";
	private static final String Q_GET_COUNT_OF_ALL_NEWS = "SELECT COUNT(*) FROM news";
	private static final String Q_GET_NEWS_BY_ID = "SELECT * FROM news WHERE id = ?";
	private static final String Q_GET_ALL_NEWS_BY_DATE_DESC = "SELECT * FROM news ORDER BY date_added DESC";

	private static final String Q_GET_ALL_NEWS_BY_DATE_DESC_LIMIT = "SELECT * FROM news ORDER BY date_added DESC LIMIT ?";

	private static final String Q_GET_COUNT_NEWS_AFTER_DATE_DESC = "SELECT * FROM news WHERE date_added > ? ORDER BY date_added LIMIT ?";

	private static final String Q_GET_COUNT_NEWS_STARTING_FROM_NUMBER = "SELECT * FROM news ORDER BY date_added DESC LIMIT ? OFFSET ?";


	@Override
	public List<News> getList() throws DaoException {
		return null;
	}

	@Override
	public List<News> getCountNewsStartingFrom(int count, int from) throws DaoException {
		return null;
	}

	@Override
	public News fetchById(int id) throws DaoException {
		return null;
	}

	@Override
	public int addNews(News news) throws DaoException {
		return 0;
	}

	@Override
	public void updateNews(News news) throws DaoException {

	}

	@Override
	public void deleteNews(int[] newsIds) throws DaoException {

	}

	@Override
	public int getTotalNewsAmount() throws DaoException {
		return 0;
	}
}
