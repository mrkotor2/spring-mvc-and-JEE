package com.training.springmvc.controller;

import com.training.login.LoginService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Log4j
@Controller
@SessionAttributes("name")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLoginPost(ModelMap model, @RequestParam String name, @RequestParam String password) {
        log.info(name);
        if (!loginService.isUserValid(name, password)) {
            model.put("errorMessage", "Invalid user");
            return "login";
        }
        model.put("name", name);
        return "welcome";
    }
}
