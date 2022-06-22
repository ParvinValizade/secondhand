package com.company.secondhand.user.service;

import com.company.secondhand.user.TestSupport;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.dto.converter.UserDtoConverter;
import com.company.secondhand.user.model.User;
import com.company.secondhand.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest extends TestSupport {

    private  UserRepository repository;
    private  UserDtoConverter converter;

    private UserService userService;

    @BeforeEach
    public void setUp(){
        converter = mock(UserDtoConverter.class);
        repository = mock(UserRepository.class);

        userService = new UserService(repository,converter);
    }

    @Test
    public void testGetAllUsers_itShouldReturnUserDtoList(){
        List<User> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);

        when(repository.findAll()).thenReturn(userList);
        when(converter.convert(userList)).thenReturn(userDtoList);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(userDtoList,result);
        verify(repository).findAll();
        verify(converter).convert(userList);
    }
}
