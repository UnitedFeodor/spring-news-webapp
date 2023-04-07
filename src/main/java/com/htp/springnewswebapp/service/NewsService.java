package com.htp.springnewswebapp.service;


import com.htp.springnewswebapp.entity.News;

import java.util.List;

public interface NewsService {

  void delete(int[] newsIds) throws ServiceException;
  void add(News news) throws ServiceException;

  void find() throws ServiceException;
  void update(News news) throws ServiceException;
  
  List<News> getCountNewsStartingFrom(int count, int from)  throws ServiceException;

  int getTotalNewsAmount() throws ServiceException;
  List<News> list()  throws ServiceException;
  News findById(int id) throws ServiceException;
}
