package com.htp.springnewswebapp.dao.impl;

import com.htp.springnewswebapp.dao.DaoException;
import com.htp.springnewswebapp.entity.News;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class NewsDAOImpl implements com.htp.springnewswebapp.dao.NewsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<News> getAllNews() throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> newsQuery = currentSession.createQuery("from News", News.class);
			return newsQuery.getResultList();
		}  catch (HibernateException e) {
			throw new DaoException("Hibernate error", e);
		}

	}

	@Override
	public List<News> getCountNewsStartingFrom(int count, int from) throws DaoException {
		return null;
	}

	@Override
	public News findById(int id) throws DaoException {
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
