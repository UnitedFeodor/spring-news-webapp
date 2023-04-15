package com.htp.springnewswebapp.controller;

import com.htp.springnewswebapp.constants.JSPConstants;
import com.htp.springnewswebapp.constants.UserConstants;
import com.htp.springnewswebapp.entity.*;
import com.htp.springnewswebapp.service.NewsService;
import com.htp.springnewswebapp.service.ServiceException;
import com.htp.springnewswebapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FrontController {

    private static final String JSP_NEWS = "news";
    private static final String AUTH_ERROR = "auth_error";
    private static final String LOGIN = "login";

    private static final String JSP_LOGIN_PARAM = "login";
    private static final String JSP_PASSWORD_PARAM = "password";

    private final NewsService newsService;
    private final UserService userService;

    @ModelAttribute("user")
    private User insertUserInModel() {
        User user = new User();
        user.setStatus(new UserStatus());
        user.setUserDetails(new UserDetails());
        user.setRole(new Role());



        return user;
    }

    @RequestMapping("/home")
    public String goToHomePage(Model model) {
        List<News> latestNews;
        try {
            latestNews = newsService.getAllNews();
            if (latestNews.size() > 0) {
                model.addAttribute(JSP_NEWS, latestNews);
            }

            return "baseLayout";
        } catch (ServiceException e) {
            return "error";
        }

    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String goToRegistrationPage(HttpServletRequest request, Model model) {
//        User user = new User();
//        model.addAttribute("user",user);
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
    public String signUp(HttpServletRequest request) {
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
            login = request.getParameter(JSP_LOGIN_PARAM);
            password = request.getParameter(JSP_PASSWORD_PARAM);

            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user = userService.signIn(user);

            if (user != null) {
                session.setAttribute(UserConstants.USER_ACTIVITY, UserConstants.USER_STATUS_ACTIVE);
                session.setAttribute(UserConstants.USER_ROLE, user.getRole());
                session.setAttribute(UserConstants.USER_ID,user.getId());

                request.setAttribute(JSPConstants.PRESENTATION, JSPConstants.NEWS_LIST);
                return "redirect:/newslist";
            } else {
                session.setAttribute(UserConstants.USER_ACTIVITY, UserConstants.USER_STATUS_NOT_ACTIVE);
                session.setAttribute(AUTH_ERROR, "wrong login or password");

                return "redirect:/home";
            }
        } catch (ServiceException e) {
            return "error";
        }

    }

    private final String JSP_COUNT_PARAM = "count";
    private final String JSP_PAGE_NUMBER_PARAM = "page";
    private final String JSP_FINAL_PAGE_NUMBER = "final_page_number";
    private final int DEFAULT_COUNT = 5;
    private final int DEFAULT_PAGE = 1;
    private final String DEFAULT_PAGE_STRING = "1";
    private final String DEFAULT_COUNT_STRING = "5";

    @RequestMapping(value = "/newslist", method = RequestMethod.GET)
    public String goToNewsList(
            @RequestParam(required = false, defaultValue = DEFAULT_PAGE_STRING , value= JSP_PAGE_NUMBER_PARAM) String pageParam,
            @RequestParam(required = false, defaultValue =  DEFAULT_COUNT_STRING, value= JSP_COUNT_PARAM) String countParam,
            HttpServletRequest request,
            Model model) {

        List<News> newsList;
        try {

            int newsPage;
//            pageParam = request.getParameter(JSP_PAGE_NUMBER_PARAM);
//            if (pageParam == null) {
//                newsPage = DEFAULT_PAGE;
//            } else {
                newsPage = Integer.parseInt(pageParam);
//            }

            //String sessionCountParam = (String) request.getSession(false).getAttribute(JSP_COUNT_PARAM);
//            countParam = request.getParameter(JSP_COUNT_PARAM);
            int newsCount;
//            if (countParam == null) {

//                if (sessionCountParam == null) {
                    newsCount = Integer.parseInt(countParam);
//                } else {
//                    newsCount = Integer.parseInt(sessionCountParam);
//                }

//            } else {
//                newsCount = Integer.parseInt(countParam);
//            }

//            if (countParam != null && sessionCountParam != null && !countParam.equals(sessionCountParam)) {
//                newsPage = DEFAULT_PAGE;
//            }
            int totalNewsAmount = newsService.getTotalNewsAmount();

            if (totalNewsAmount < newsCount*newsPage) {
                newsPage = DEFAULT_PAGE;
            }


            newsList = newsService.getCountNewsStartingFrom(newsCount,newsPage-1);
            if (newsList.size() > 0) {
                model.addAttribute(JSP_NEWS, newsList);
//                request.setAttribute(JSPConstants.NEWS, newsList);
            }

            int finalPageNumber = totalNewsAmount % newsCount == 0 ? totalNewsAmount / newsCount : totalNewsAmount / newsCount + 1;

            request.setAttribute(JSP_FINAL_PAGE_NUMBER,finalPageNumber);
            request.setAttribute(JSP_PAGE_NUMBER_PARAM, newsPage);
            request.setAttribute(JSP_COUNT_PARAM, countParam);
//            request.getSession(false).setAttribute(JSP_COUNT_PARAM,String.valueOf(newsCount));

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
}
