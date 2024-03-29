package com.example.SpringSecurityClient.service;

import com.example.SpringSecurityClient.entity.User;
import com.example.SpringSecurityClient.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationToken(User user, String token);

    boolean validateToken(String token);
}
