package org.ebook.kin.services;

import org.ebook.kin.data.model.User;
import org.ebook.kin.data.repository.UserRepository;
import org.ebook.kin.dtos.request.SignInRequest;
import org.ebook.kin.dtos.request.SignUpRequest;
import org.ebook.kin.exceptions.IncorrectCredentialsException;
import org.ebook.kin.exceptions.UserAlreadyExistException;
import org.ebook.kin.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void doThisFirst(){
        userRepository.deleteAll();
    }

    @Test
    public void testThatNewUserCanSignUp() throws UserAlreadyExistException {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("gadaphi@gmail.com");
        signUpRequest.setUsername("gadaphi");
        signUpRequest.setPhoneNumber("08166672396");
        signUpRequest.setPassword("password");

        userService.register(signUpRequest);
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void testForUniqueUser() throws UserAlreadyExistException {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("anu@gmail.com");
        signUpRequest.setUsername("Anu");
        signUpRequest.setPassword("password");
        userService.register(signUpRequest);
        assertThat(userRepository.count(), is(1L));

        SignUpRequest registerUserRequest1 = new SignUpRequest();
        registerUserRequest1.setUsername("Anu");
        registerUserRequest1.setPassword("password");
        assertThrows(UserAlreadyExistException.class, ()-> userService.register(registerUserRequest1));
    }
    @Test
    public void testThatRegisteredUserCanLogin() throws UserAlreadyExistException, UserNotFoundException, IncorrectCredentialsException {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("Anu");
        signUpRequest.setPassword("password");
        userService.register(signUpRequest);
        assertThat(userRepository.count(), is(1L));

        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername("Anu");
        signInRequest.setPassword("password");

        User user = userService.signIn(signInRequest);
        assertThat(user.isLogIn(), is(true));
    }

}