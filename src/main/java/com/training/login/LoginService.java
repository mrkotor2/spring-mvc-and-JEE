package com.training.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService { //TODO Maybe I should move JEE to the new module
    public boolean isUserValid(String user, String password) {
        return user.equals("Alex") && password.equals("123");
    }
}