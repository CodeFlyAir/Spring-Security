package com.example.SpringSecurityClient.event.listener;

import com.example.SpringSecurityClient.entity.User;
import com.example.SpringSecurityClient.event.RegistrationCompletionEvent;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public class RegistrationCompletionEventListener
        implements ApplicationListener<RegistrationCompletionEvent> {
    @Override
    public void onApplicationEvent(RegistrationCompletionEvent event) {
        //Create Verification Link with Token
        User user=event.getUser();
        String token= UUID.randomUUID().toString();

        // Send Mail to User
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
