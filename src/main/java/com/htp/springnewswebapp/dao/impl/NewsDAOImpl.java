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

	private final SessionFactory sessionFactory;

	@Autowired
	public NewsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<News> getAllNews() throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> newsQuery = currentSession.createQuery("from News order by dateAdded DESC", News.class);
			return newsQuery.getResultList();
		}  catch (HibernateException e) {
			throw new DaoException("Hibernate error", e);
		}

	}

	@Override
	public List<News> getCountNewsStartingFrom(int count, int from) throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<News> newsQuery = currentSession.createQuery("from News order by dateAdded DESC", News.class).setFirstResult(from*count).setMaxResults(count);
			return newsQuery.getResultList();
		}  catch (HibernateException e) {
			throw new DaoException("Hibernate error", e);
		}
	}

	@Override
	public News findById(int id) throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			News news = currentSession.get(News.class, id);
			return news;
		}  catch (HibernateException e) {
			throw new DaoException("Hibernate error", e);
		}
	}

	@Override
	public Integer addNews(News news) throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Integer savedId = (Integer) currentSession.save(news);
			return savedId;
		}  catch (HibernateException e) {
			throw new DaoException("Hibernate error", e);
		}
	}

	@Override
	public void updateNews(News news) throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.update(news);
		} catch (HibernateException e) {
			throw new DaoException("Hibernate error", e);
		}
	}

	@Override
	public void deleteNews(int[] newsIds) throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.createQuery("delete from News news where news.id in (:newsIds)", News.class)
					.setParameterList("newsIds",List.of(newsIds))
					.executeUpdate();
		} catch (HibernateException e) {
			throw new DaoException("Hibernate error", e);
		}
	}

	@Override
	public int getTotalNewsAmount() throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();

			return (int)((long)currentSession.createQuery("select count(*) from News").getSingleResult());
		} catch (HibernateException e) {
			throw new DaoException("Hibernate error", e);
		}
	}
}
