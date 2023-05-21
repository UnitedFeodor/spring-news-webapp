package com.htp.springnewswebapp.dao.impl;

import com.htp.springnewswebapp.dao.UserDaoException;
import com.htp.springnewswebapp.entity.News;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class NewsDAOImpl implements com.htp.springnewswebapp.dao.NewsDAO {
	private final SessionFactory sessionFactory;

	@Autowired
	public NewsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<News> getAllNews() throws UserDaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();

			Query<News> newsQuery = currentSession
					.createQuery("FROM News n INNER JOIN FETCH n.author ORDER BY n.dateAdded DESC", News.class);

			return newsQuery.getResultList();
		}  catch (HibernateException e) {
			throw new UserDaoException("Hibernate error", e);
		}

	}

	@Override
	public List<News> getCountNewsStartingFrom(int count, int from) throws UserDaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();

			Query<News> newsQuery = currentSession
					.createQuery("FROM News n INNER JOIN FETCH n.author ORDER BY n.dateAdded DESC", News.class)
					.setFirstResult(from*count)
					.setMaxResults(count);

			return newsQuery.getResultList();
		}  catch (HibernateException e) {
			throw new UserDaoException("Hibernate error", e);
		}
	}

	@Override
	public News findById(int id) throws UserDaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			News news = currentSession
					.createQuery("SELECT n FROM News n INNER JOIN FETCH n.author WHERE n.id = :id", News.class)
					.setParameter("id", id)
					.uniqueResult();

			if (news != null) {
				Hibernate.initialize(news.getStatus());
			}

			return news;
		}  catch (HibernateException e) {
			throw new UserDaoException("Hibernate error", e);
		}
	}

	@Override
	public Integer addNews(News news) throws UserDaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();

			return (Integer) currentSession
					.save(news);
		}  catch (HibernateException e) {
			throw new UserDaoException("Hibernate error", e);
		}
	}

	@Override
	public void updateNews(News news) throws UserDaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();

			currentSession.update(news);

		} catch (HibernateException e) {
			throw new UserDaoException("Hibernate error", e);
		}
	}

	@Override
	public void deleteNews(int[] newsIds) throws UserDaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			List<Integer> newsIdsList = Arrays.stream(newsIds).boxed().collect(Collectors.toList());

			currentSession
					.createQuery("DELETE FROM News news WHERE news.id IN (:newsIds)")
					.setParameterList("newsIds",newsIdsList)
					.executeUpdate();
		} catch (HibernateException e) {
			throw new UserDaoException("Hibernate error", e);
		}
	}

	@Override
	public int getTotalNewsAmount() throws UserDaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();

			return (int)((long)currentSession
					.createQuery("SELECT count(*) FROM News")
					.getSingleResult());

		} catch (HibernateException e) {
			throw new UserDaoException("Hibernate error", e);
		}
	}
}
