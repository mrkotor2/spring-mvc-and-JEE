package com.training;

public class UserValidationService {
    public boolean isUserValid(String user, String password) {
        return user.equals("Alex") && password.equals("123");
    }
}
