package com.htp.springnewswebapp.service.impl;


import com.htp.springnewswebapp.dao.DaoException;
import com.htp.springnewswebapp.dao.NewsDAO;
import com.htp.springnewswebapp.entity.News;
import com.htp.springnewswebapp.service.NewsService;
import com.htp.springnewswebapp.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsDAO newsDAO;

	@Transactional
	@Override
	public void delete(int[] newsIds) throws ServiceException {
		News news = new News();
		for (int currId: newsIds) {
			news.setId(currId);

		}
		try {
			newsDAO.deleteNews(newsIds);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	@Override
	public void add(News news) throws ServiceException {
		try {
			newsDAO.addNews(news);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}
	@Transactional
	@Override
	public int getTotalNewsAmount() throws ServiceException {
		try {
			return newsDAO.getTotalNewsAmount();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	@Override
	public void update(News news)  throws ServiceException{
		try {
			newsDAO.updateNews(news);
		} catch (DaoException e) {
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
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Transactional
	@Override
	public List<News> getAllNews() throws ServiceException {
		try {
			return newsDAO.getAllNews();
		} catch (DaoException e) {
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
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
