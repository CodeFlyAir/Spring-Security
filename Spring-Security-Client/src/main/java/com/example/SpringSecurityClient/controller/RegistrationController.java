package com.example.SpringSecurityClient.controller;

import com.example.SpringSecurityClient.entity.User;
import com.example.SpringSecurityClient.event.RegistrationCompletionEvent;
import com.example.SpringSecurityClient.model.UserModel;
import com.example.SpringSecurityClient.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/register")
    private String registerUser
            (@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        eventPublisher.publishEvent(new RegistrationCompletionEvent(
                user,
                getApplicationUrl(request)
        ));
        return "Success";
    }

    @GetMapping("/verify")
    private String verifyToken(@RequestParam("token") String token){
        if(userService.validateToken(token))
            return "User Verified";
        return "Bad User";
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();
    }
}
