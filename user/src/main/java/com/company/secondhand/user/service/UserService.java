package com.company.secondhand.user.service;

import com.company.secondhand.user.dto.CreateUserRequest;
import com.company.secondhand.user.dto.UpdateUserRequest;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.dto.converter.UserDtoConverter;
import com.company.secondhand.user.exception.UserIsNotActiveException;
import com.company.secondhand.user.exception.UserNotFoundException;
import com.company.secondhand.user.model.User;
import com.company.secondhand.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAllUsers() {
        return userDtoConverter.convert(userRepository.findAll());
    }

    public UserDto getUserByMail(String mail) {
        User user = findUserByMail(mail);
        return userDtoConverter.convert(user);
    }

    public UserDto createUser(final CreateUserRequest userRequest) {
        User user = new User(
                userRequest.getMail(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getMiddleName(),
                false
        );
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto updateUser(final String mail,final UpdateUserRequest updateUserRequest) {
        User user = findUserByMail(mail);
        if (Boolean.FALSE.equals(user.isActive())) {
            throw new UserIsNotActiveException("The user wanted update is not active, user mail: " + user.getMail());
        }
        User updatedUser = new User(user.getId(),
                updateUserRequest.getMail(),
                updateUserRequest.getFirstName(),
                updateUserRequest.getLastName(),
                updateUserRequest.getMiddleName(),
                true
        );
        return userDtoConverter.convert(userRepository.save(updatedUser));
    }

    public void deactivateUser(final Long id) {
        changeStatusUser(id, false);
    }

    public void activateUser(final Long id) {
        changeStatusUser(id, true);
    }

    public void deleteUser(final Long id) {
            findUserById(id);
            userRepository.deleteById(id);

    }

    private void changeStatusUser(final Long id, Boolean status) {
        User user = findUserById(id);
        User updatedUser = new User(user.getId(),
                user.getMail(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                status
        );

        userRepository.save(updatedUser);

    }

    private User findUserByMail(String mail) {
        return userRepository.findByMail(mail).
                orElseThrow(() -> new UserNotFoundException("User couldn't be found by following mail: " + mail));
    }

    protected User findUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User couldn't be found by following id: " + id));
    }

}
