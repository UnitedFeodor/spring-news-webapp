package com.htp.springnewswebapp.controller;

import com.htp.springnewswebapp.entity.News;
import com.htp.springnewswebapp.service.NewsService;
import com.htp.springnewswebapp.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class FrontController {

    private static final String JSP_NEWS = "news";
    @Autowired
    private NewsService newsService;

    @RequestMapping("/home")
    public String goToHomePage(HttpServletRequest request, HttpServletResponse response) {
        List<News> latestNews;
        try {
            latestNews = newsService.getAllNews();
            if(latestNews.size() > 0) {
                request.setAttribute(JSP_NEWS, latestNews);
            }

            return "baseLayout";
        } catch (ServiceException e) {
            return "error";
        }


    }
}
