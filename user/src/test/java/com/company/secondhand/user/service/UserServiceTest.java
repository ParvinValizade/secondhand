package com.company.secondhand.user.service;

import com.company.secondhand.user.TestSupport;
import com.company.secondhand.user.dto.CreateUserRequest;
import com.company.secondhand.user.dto.UpdateUserRequest;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.dto.converter.UserDtoConverter;
import com.company.secondhand.user.exception.UserIsNotActiveException;
import com.company.secondhand.user.exception.UserNotFoundException;
import com.company.secondhand.user.model.User;
import com.company.secondhand.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    public void testGetUserByMail_whenUserMailExist_itShouldReturnUserDto(){
        String mail = "parvin.valizade@mail.ru";
        User user = generateUser(mail);
        UserDto userDto = generateUserDto(mail);

        when(repository.findByMail(mail)).thenReturn(Optional.of(user));
        when(converter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserByMail(mail);

        assertEquals(userDto,result);
        verify(repository).findByMail(mail);
        verify(converter).convert(user);
    }

    @Test
    public void testGetUserByMail_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){
        String mail = "parvin.valizade@mail.ru";

        when(repository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,() ->
                userService.getUserByMail(mail));

        verify(repository).findByMail(mail);
        verifyNoInteractions(converter);
    }

    @Test
    public void testCreateUser_itShouldReturnCreatedUserDto(){
        String mail = "parvin.valizade@mail.ru";
        CreateUserRequest request = new CreateUserRequest(mail,"Parvin","Valizade","Pempi");
        User user = new User(mail,"Parvin","Valizade","Pempi",false);
        User savedUser = new User(1l,mail,"Parvin","Valizade","Pempi",false);
        UserDto userDto = new UserDto(mail,"Parvin","Valizade","Pempi",new ArrayList<>());

        when(repository.save(user)).thenReturn(savedUser);
        when(converter.convert(savedUser)).thenReturn(userDto);

        UserDto result = userService.createUser(request);

        assertEquals(userDto,result);

        verify(repository).save(user);
        verify(converter).convert(savedUser);

    }

    @Test
    public void testUpdateUser_whenUserMailExistAndUserIsActive_itShouldReturnUpdatedUserDto(){
        String mail = "parvin.valizade@mail.ru";
        UpdateUserRequest request = new UpdateUserRequest(mail,"Parvin","Valizade","Ostwind");
        User user = new User(1L,mail,"Parvin","Valizade","Pempi",true);
        User savedUser = new User(1L,mail,"Parvin","Valizade","Ostwind",true);
        User updatedUser = new User(1L,mail,"Parvin","Valizade","Ostwind",true);
        UserDto userDto = new UserDto(mail,"Parvin","Valizade","Ostwind",new ArrayList<>());

        when(repository.findByMail(mail)).thenReturn(Optional.of(user));
        when(repository.save(updatedUser)).thenReturn(savedUser);
        when(converter.convert(savedUser)).thenReturn(userDto);

        UserDto result = userService.updateUser(mail,request);

        assertEquals(userDto,result);

        verify(repository).findByMail(mail);
        verify(repository).save(updatedUser);
        verify(converter).convert(savedUser);

    }

    @Test
    public void testUpdateUser_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){
        String mail = "parvin.valizade@mail.ru";
        UpdateUserRequest request = new UpdateUserRequest(mail,"Parvin","Valizade","Ostwind");

        when(repository.findByMail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(mail,request));

        verify(repository).findByMail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);

    }

    @Test
    public void testUpdateUser_whenUserMailExistButUserIsNotActive_itShouldThrowUserNotActiveException(){
        String mail = "parvin.valizade@mail.ru";

        UpdateUserRequest request = new UpdateUserRequest(mail,"Parvin","Valizade","Ostwind");
        User user = new User(1L,mail,"Parvin","Valizade","Pempi",false);

        when(repository.findByMail(mail)).thenReturn(Optional.of(user));

        assertThrows(UserIsNotActiveException.class,
                () -> userService.updateUser(mail,request));

        verify(repository).findByMail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);

    }

    @Test
    public void testDeactivateUser_whenUserIdExist_itShouldUpdateUserByActiveFalse(){
        String mail = "parvin.valizade@mail.ru";

        User user = new User(userId,mail,"Parvin","Valizade","Pempi",true);
        User savedUser = new User(userId,mail,"Parvin","Valizade","Pempi",false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.deactivateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testDeactivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () ->userService.deactivateUser(userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testActivateUser_whenUserIdExist_itShouldUpdateUserByActive(){
        String mail = "parvin.valizade@mail.ru";

        User user = new User(userId,mail,"Parvin","Valizade","Pempi",false);
        User savedUser = new User(userId,mail,"Parvin","Valizade","Pempi",true);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.activateUser(userId);

        verify(repository).findById(userId);
        verify(repository).save(savedUser);

    }

    @Test
    public void testActivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () ->userService.activateUser(userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }

    @Test
    public void testDeleteUser_whenUserIdExist_itShouldDeleteUser(){
        String mail = "parvin.valizade@mail.ru";

        User user = new User(userId,mail,"Parvin","Valizade","Pempi",false);

        when(repository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(repository).findById(userId);
        verify(repository).deleteById(userId);

    }

    @Test
    public void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){

        when(repository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () ->userService.deleteUser(userId));

        verify(repository).findById(userId);
        verifyNoMoreInteractions(repository);

    }
}
