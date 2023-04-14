package com.htp.springnewswebapp.controller;

import com.htp.springnewswebapp.constants.JSPConstants;
import com.htp.springnewswebapp.constants.UserConstants;
import com.htp.springnewswebapp.entity.News;
import com.htp.springnewswebapp.entity.User;
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


    @RequestMapping("/home")
    public String goToHomePage(Model model) {
        List<News> latestNews;
        try {
            latestNews = newsService.getAllNews();
            if(latestNews.size() > 0) {
                model.addAttribute(JSP_NEWS, latestNews);
            }

            return "baseLayout";
        } catch (ServiceException e) {
            return "error";
        }

    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String goToRegistrationPage(HttpServletRequest request, Model model) {
        User user = new User();
        model.addAttribute("user",user);
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


    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);	// TODO add user cabinet to change details and see them
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

                return "newsList";
                //response.sendRedirect(JSPConstants.CONTROLLER_GO_TO_NEWS_LIST);
            } else {
                session.setAttribute(UserConstants.USER_ACTIVITY, UserConstants.USER_STATUS_NOT_ACTIVE);
                session.setAttribute(AUTH_ERROR, "wrong login or password");

                return "home";
            }
        } catch (ServiceException e) {
            return "error";
        }

    }
}
