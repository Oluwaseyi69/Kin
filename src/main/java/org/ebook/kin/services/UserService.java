package org.ebook.kin.services;

import org.ebook.kin.data.model.User;
import org.ebook.kin.dtos.request.SignInRequest;
import org.ebook.kin.dtos.request.SignUpRequest;
import org.ebook.kin.dtos.response.SignUpResponse;
import org.ebook.kin.exceptions.IncorrectCredentialsException;
import org.ebook.kin.exceptions.UserAlreadyExistException;
import org.ebook.kin.exceptions.UserNotFoundException;

public interface UserService {
    SignUpResponse register (SignUpRequest signupRequest) throws UserAlreadyExistException;

    User signIn(SignInRequest signInRequest) throws UserNotFoundException, IncorrectCredentialsException;
}
