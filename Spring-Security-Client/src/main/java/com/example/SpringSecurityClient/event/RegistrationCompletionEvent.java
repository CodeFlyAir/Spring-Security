package com.example.SpringSecurityClient.event;

import com.example.SpringSecurityClient.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompletionEvent extends ApplicationEvent {
    private User user;
    private String url;
    public RegistrationCompletionEvent(User user, String url) {
        super(user);
        this.user = user;
        this.url = url;
    }
}
