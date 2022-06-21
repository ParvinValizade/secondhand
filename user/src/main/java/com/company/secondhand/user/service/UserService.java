package com.company.secondhand.user.service;

import com.company.secondhand.user.dto.CreateUserRequest;
import com.company.secondhand.user.dto.UpdateUserRequest;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.dto.converter.UserDtoConverter;
import com.company.secondhand.user.exception.UserNotFoundException;
import com.company.secondhand.user.repository.UserRepository;
import com.company.secondhand.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUserByMail(String mail) {
        User user = findUserByMail(mail);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(CreateUserRequest userRequest) {
        User user = new User(
                null,
                userRequest.getMail(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getMiddleName()
        );
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(String mail, UpdateUserRequest updateUserRequest) {
        User user = findUserByMail(mail);
        User updatedUser = new User(user.getId(),
                updateUserRequest.getMail(),
                updateUserRequest.getFirstName(),
                updateUserRequest.getLastName(),
                updateUserRequest.getMiddleName()
        );
        return userDtoConverter.convert(userRepository.save(updatedUser));
    }
    private User findUserByMail(String mail){
        return userRepository.findByMail(mail).
                orElseThrow(()->new UserNotFoundException("User couldn't be found by following mail: "+mail));
    }
}
