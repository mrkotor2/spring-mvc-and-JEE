package com.training.springmvc;

import com.training.login.UserValidationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j
@Controller
public class LoginController {

     final UserValidationService userValidationService;

    @Autowired
    public LoginController(UserValidationService userValidationService) {
        this.userValidationService = userValidationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLoginPost(@RequestParam String name, @RequestParam String password, ModelMap model) {
        log.info(name);
        if (userValidationService.isUserValid(name, password)) {
            model.put("name", name);
            model.put("password", password);
            return "welcome";
        } else {
            model.put("errorMessage", "Invalid user");
            return "login";
        }

    }
}
