package org.ebook.kin.utils;

import org.ebook.kin.data.model.User;
import org.ebook.kin.dtos.request.SignUpRequest;
import org.ebook.kin.dtos.response.SignUpResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static SignUpResponse map(User user){
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setUsername(user.getUsername());
        signUpResponse.setDateRegistered(DateTimeFormatter
                .ofPattern("EEE dd/MMM/yyyy HH:mm:ss a")
                .format(LocalDateTime.now()));
        return signUpResponse;
    }
    public static User map(SignUpRequest signUpRequest){
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());

        return user;
    }

}
