package com.htp.springnewswebapp.controller;

import com.htp.springnewswebapp.constants.JSPConstants;
import com.htp.springnewswebapp.constants.UserConstants;
import com.htp.springnewswebapp.entity.*;
import com.htp.springnewswebapp.service.NewsService;
import com.htp.springnewswebapp.service.ServiceException;
import com.htp.springnewswebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class FrontController {

    private final NewsService newsService;
    private final UserService userService;

    @Autowired
    public FrontController(NewsService newsService, UserService userService) {
        this.newsService = newsService;
        this.userService = userService;
    }

    @ModelAttribute("user")
    private User insertUserInModel() {
        User user = new User();
        user.setStatus(new UserStatus());
        user.setUserDetails(new UserDetails());
        user.setRole(new Role());
        UserDetails userDetails = user.getUserDetails();
        userDetails.setUser(user);

        return user;
    }

    @ModelAttribute("news")
    private News insertNewsInModel() {
        News news = new News();
        news.setStatus(new NewsStatus());
        news.setAuthor(new User());
        return news;
    }

    @RequestMapping("/home")
    public String goToHomePage(Model model) {
        List<News> latestNews;
        try {
            latestNews = newsService.getAllNews();
            if (latestNews.size() > 0) {
                model.addAttribute(JSPConstants.JSP_NEWS, latestNews);
            }

            return "baseLayout";
        } catch (ServiceException e) {
            return "error";
        }

    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String goToRegistrationPage(HttpServletRequest request, Model model) {
        request.setAttribute(JSPConstants.PRESENTATION, JSPConstants.REGISTRATION);
        return "baseLayout";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("user") User user, HttpServletRequest request, Model model) {
        try {
            userService.signUp(user);
            return "redirect:/home";
        } catch (ServiceException e) {
            return "error";
        }

    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        session.setAttribute(UserConstants.USER_ACTIVITY, UserConstants.USER_STATUS_NOT_ACTIVE);
        session.setAttribute(UserConstants.USER_ROLE, UserConstants.ROLE_GUEST);
        session.removeAttribute(UserConstants.USER_ID);
        return "redirect:/home";


    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request) {
        // TODO creation in filter
        HttpSession session = request.getSession(true);	// TODO add user cabinet to change details and see them
        try {
            String login;
            String password;
            login = request.getParameter(JSPConstants.JSP_LOGIN_PARAM);
            password = request.getParameter(JSPConstants.JSP_PASSWORD_PARAM);

            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user = userService.signIn(user);

            if (user != null) {
                session.setAttribute(UserConstants.USER_ACTIVITY, UserConstants.USER_STATUS_ACTIVE);
                session.setAttribute(UserConstants.USER_ROLE, user.getRole().getTitle());
                session.setAttribute(UserConstants.USER_ID,user.getId());

                request.setAttribute(JSPConstants.PRESENTATION, JSPConstants.NEWS_LIST);
                return "redirect:/newslist";
            } else {
                session.setAttribute(UserConstants.USER_ACTIVITY, UserConstants.USER_STATUS_NOT_ACTIVE);
                session.setAttribute(JSPConstants.AUTH_ERROR, "wrong login or password");

                return "redirect:/home";
            }
        } catch (ServiceException e) {
            return "error";
        }

    }


    @RequestMapping(value = "/newslist", method = RequestMethod.GET)
    public String goToNewsList(
            @RequestParam(required = false, defaultValue = JSPConstants.DEFAULT_PAGE_STRING , value= JSPConstants.JSP_PAGE_NUMBER_PARAM) String pageParam,
            @RequestParam(required = false, defaultValue =  JSPConstants.DEFAULT_COUNT_STRING, value= JSPConstants.JSP_COUNT_PARAM) String countParam,
            HttpServletRequest request,
            Model model) {

        List<News> newsList;
        try {
            int newsPage = Integer.parseInt(pageParam);
            int newsCount = Integer.parseInt(countParam);
            int totalNewsAmount = newsService.getTotalNewsAmount();

            if (totalNewsAmount < newsCount*newsPage) {
                newsPage = JSPConstants.DEFAULT_PAGE;
            }

            newsList = newsService.getCountNewsStartingFrom(newsCount,newsPage-1);
            if (newsList.size() > 0) {
                model.addAttribute(JSPConstants.JSP_NEWS, newsList);
            }

            int finalPageNumber = totalNewsAmount % newsCount == 0 ? totalNewsAmount / newsCount : totalNewsAmount / newsCount + 1;

            request.setAttribute(JSPConstants.JSP_FINAL_PAGE_NUMBER,finalPageNumber);
            request.setAttribute(JSPConstants.JSP_PAGE_NUMBER_PARAM, newsPage);
            request.setAttribute(JSPConstants.JSP_COUNT_PARAM, countParam);

            request.setAttribute(JSPConstants.PRESENTATION, JSPConstants.NEWS_LIST);

            return "baseLayout";

        } catch (ServiceException e) {
            HttpSession session = request.getSession(false);
            session.setAttribute(JSPConstants.ERROR_MESSAGE,"cannot get the list of news");
            return "error";
        } catch (NumberFormatException e) {
            HttpSession session = request.getSession(false);
            session.setAttribute(JSPConstants.ERROR_MESSAGE,"invalid request parameters");
            return "error";
        }

    }

    @RequestMapping(value = "/view/{id}",method = RequestMethod.GET)
    public String goToViewNews(
            @PathVariable int id,
            HttpServletRequest request,
            Model model) {
        try {
            News news  = newsService.findById(id);
            model.addAttribute(JSPConstants.NEWS, news);
            request.setAttribute(JSPConstants.PRESENTATION, JSPConstants.VIEW_NEWS);

            return "baseLayout";

        } catch (ServiceException e) {
            HttpSession session = request.getSession(false);
            session.setAttribute(JSPConstants.ERROR_MESSAGE,"cannot find the news by id");
            return "error";
        }
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String goToEditNews(
            @PathVariable int id,
            HttpServletRequest request,
            Model model) {
        try {
            News news  = newsService.findById(id);
            model.addAttribute(JSPConstants.NEWS, news);
            request.setAttribute(JSPConstants.PRESENTATION, JSPConstants.EDIT_NEWS);

            return "baseLayout";

        } catch (ServiceException e) {
            HttpSession session = request.getSession(false);
            session.setAttribute(JSPConstants.ERROR_MESSAGE, "cannot find the news by id");
            return "error";
        }
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.POST)
    public String editNews(
            @ModelAttribute("news") News news,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        try {

            newsService.update(news);
            session.setAttribute(JSPConstants.JSP_SAVE_SUCCESS, JSPConstants.SUC);
            return "redirect:/newslist";

        } catch (ServiceException e) {
            session.setAttribute(JSPConstants.ERROR_MESSAGE, "edit error");
            return "error";

        }
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String goToAddNews(HttpServletRequest request, Model model) {
        request.setAttribute(JSPConstants.PRESENTATION, JSPConstants.ADD_NEWS);
        return "baseLayout";

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addNews(
            @ModelAttribute("news") News news,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        try {
            int userId = (int) session.getAttribute(UserConstants.USER_ID);
            User author = new User();
            author.setId(userId);

            news.setAuthor(author);

            newsService.add(news);
            session.setAttribute(JSPConstants.JSP_SAVE_SUCCESS, JSPConstants.SUC);
            return "redirect:/newslist";

        } catch (ServiceException e) {
            session.setAttribute(JSPConstants.ERROR_MESSAGE, "add error");
            return "error";

        }
    }


    @RequestMapping(value = "/deletenews",method = RequestMethod.POST)
    public String deleteSeveralNews(
            @RequestParam("id")String[] newsIdsStringArr,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        int[] newsIds = Stream.of(newsIdsStringArr).mapToInt(Integer::parseInt).toArray();
        if (newsIds != null) {
            try {
                newsService.delete(newsIds);
                session.setAttribute(JSPConstants.JSP_DELETE_SUCCESS,JSPConstants.SUC);
                return "redirect:/newslist";
            } catch (ServiceException e) {
                session.setAttribute(JSPConstants.ERROR_MESSAGE,"delete error");
                return "error";
            }

        } else {
            session.setAttribute(JSPConstants.ERROR_MESSAGE,"no news to delete selected");
            return "error";

        }
    }

    @RequestMapping(value = "/language", method = RequestMethod.GET)
    public String changeLanguage(
            @RequestParam(required = false, value= JSPConstants.LOCALE) String local,
            HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.setAttribute(JSPConstants.LOCALE, local);

        return UserConstants.USER_STATUS_ACTIVE.equals(session.getAttribute(UserConstants.USER_ACTIVITY))
                ? "redirect:/newslist"
                : "redirect:/home";


    }
}
