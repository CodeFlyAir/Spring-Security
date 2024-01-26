package com.example.SpringSecurityClient.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {
    private static final int TOKEN_EXPIRATION_DURATION = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN")
    )
    private User user;
    private String token;
    private Date expirationTime;

    public VerificationToken(User user, String token) {
        super();
        this.user = user;
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }

    public VerificationToken(String token) {
        super();
        this.token = token;
        this.expirationTime = calculateExpirationTime();
    }

    private @NotNull Date calculateExpirationTime() {
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, VerificationToken.TOKEN_EXPIRATION_DURATION);
        return new Date(calendar.getTime().getTime());
    }
}
