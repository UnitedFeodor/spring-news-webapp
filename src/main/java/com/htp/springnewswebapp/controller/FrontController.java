package com.htp.springnewswebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

    @RequestMapping("/home")
    public String goToHomePage() {

        return "home";
    }
}
