package com.example.SpringSecurityClient.event.listener;

import com.example.SpringSecurityClient.entity.User;
import com.example.SpringSecurityClient.event.RegistrationCompletionEvent;
import com.example.SpringSecurityClient.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompletionEventListener
        implements ApplicationListener<RegistrationCompletionEvent> {

    @Autowired
    private UserService userService;


    @Override
    public void onApplicationEvent(RegistrationCompletionEvent event) {
        //Create Verification Link with Token
        User user=event.getUser();
        String token= UUID.randomUUID().toString();
        userService.saveVerificationToken(user,token);

        // Send Mail to User
        String url=event.getUrl() + "verify?token="+token;
        log.info("Verification Link : {}",url);
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
