package com.example.SpringSecurityClient.service;

import com.example.SpringSecurityClient.entity.User;
import com.example.SpringSecurityClient.entity.VerificationToken;
import com.example.SpringSecurityClient.model.UserModel;
import com.example.SpringSecurityClient.repository.UserRepository;
import com.example.SpringSecurityClient.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void saveVerificationToken(User user, String token) {
        VerificationToken verificationToken =
                new VerificationToken(user, token);

        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public boolean validateToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null)
            return false;

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if (verificationToken.getExpirationTime().getTime()
                - cal.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return false;
        }
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }
}
