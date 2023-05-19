package com.htp.springnewswebapp.service.impl;


import com.htp.springnewswebapp.dao.UserDaoException;
import com.htp.springnewswebapp.dao.NewsDAO;
import com.htp.springnewswebapp.entity.News;
import com.htp.springnewswebapp.entity.NewsStatus;
import com.htp.springnewswebapp.service.NewsService;
import com.htp.springnewswebapp.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

	private static final int DB_ON_CREATION_NEWS_STATUS_ID = 1;
	private final NewsDAO newsDAO;

	@Autowired
	public NewsServiceImpl(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	@Transactional
	@Override
	public void delete(int[] newsIds) throws ServiceException {
		News news = new News();
		for (int currId: newsIds) {
			news.setId(currId);

		}
		try {
			newsDAO.deleteNews(newsIds);
		} catch (UserDaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	@Override
	public void add(News news) throws ServiceException {
		try {
			NewsStatus newsStatus = new NewsStatus();
			newsStatus.setId(DB_ON_CREATION_NEWS_STATUS_ID);
			news.setStatus(newsStatus);

			newsDAO.addNews(news);
		} catch (UserDaoException e) {
			throw new ServiceException(e);
		}

	}
	@Transactional
	@Override
	public int getTotalNewsAmount() throws ServiceException {
		try {
			return newsDAO.getTotalNewsAmount();
		} catch (UserDaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	@Override
	public void update(News news)  throws ServiceException{
		try {
			newsDAO.updateNews(news);
		} catch (UserDaoException e) {
			throw new ServiceException(e);
		}
		
	}
	@Transactional
	@Override
	public List<News> getCountNewsStartingFrom(int count, int from) throws ServiceException {
		if (count <= 0) {
			throw new ServiceException("invalid number of news requested");
		}
		if (from < 0) {
			throw new ServiceException("invalid page requested");
		}

		try {
			return newsDAO.getCountNewsStartingFrom(count, from);

		} catch (UserDaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	@Override
	public List<News> getAllNews() throws ServiceException {
		try {
			return newsDAO.getAllNews();
		} catch (UserDaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	@Override
	public News findById(int id) throws ServiceException {
		News news = new News();
		news.setId(id);
		try {
			return newsDAO.findById(id);
		} catch (UserDaoException e) {
			throw new ServiceException(e);
		}
	}

}
