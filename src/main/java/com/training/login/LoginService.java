package com.training.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public boolean isUserValid(String user, String password) {
        return user.equals("Alex") && password.equals("123");
    }
}
