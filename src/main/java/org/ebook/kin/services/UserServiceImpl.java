package org.ebook.kin.services;

import org.ebook.kin.data.model.User;
import org.ebook.kin.data.repository.UserRepository;
import org.ebook.kin.dtos.request.SignInRequest;
import org.ebook.kin.dtos.request.SignUpRequest;
import org.ebook.kin.dtos.response.SignUpResponse;
import org.ebook.kin.exceptions.IncorrectCredentialsException;
import org.ebook.kin.exceptions.UserAlreadyExistException;
import org.ebook.kin.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.ebook.kin.utils.Mapper.map;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private  UserRepository userRepository;
    @Override
    public SignUpResponse register(SignUpRequest signupRequest) throws UserAlreadyExistException {
        findUser(signupRequest);
        return map(userRepository.save(map(signupRequest)));
    }

    @Override
    public User signIn(SignInRequest signInRequest) throws UserNotFoundException, IncorrectCredentialsException {
        Optional<User> user = getUser(signInRequest.getUsername());
        if (user.isEmpty()) throw new UserNotFoundException("User Not Found");
        if (!user.get().getPassword().equals(signInRequest.getPassword()))
            throw new IncorrectCredentialsException("Incorrect Credentials");

        User user1 = user.get();
        user1.setLogIn(true);
        userRepository.save(user1);
        return user1;
    }

    private void findUser(SignUpRequest signupRequest) throws UserAlreadyExistException {
        Optional<User> user = userRepository.findByUsername(signupRequest.getUsername());
        if(user.isPresent())
            throw new UserAlreadyExistException("Farmer Already Exist");
    }

    private Optional<User> getUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }


}
