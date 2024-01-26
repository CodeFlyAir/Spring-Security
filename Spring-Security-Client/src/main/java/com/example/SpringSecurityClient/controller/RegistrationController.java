package com.example.SpringSecurityClient.controller;

import com.example.SpringSecurityClient.entity.User;
import com.example.SpringSecurityClient.event.RegistrationCompletionEvent;
import com.example.SpringSecurityClient.model.UserModel;
import com.example.SpringSecurityClient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    private String registerUser(@RequestBody UserModel userModel) {
        User user = userService.registerUser(userModel);
        eventPublisher.publishEvent(new RegistrationCompletionEvent(
                user,
                "url"
        ));
        return "Success";
    }
}
