package com.company.secondhand.user.service;

import com.company.secondhand.user.TestSupport;
import com.company.secondhand.user.dto.CreateUserDetailsRequest;
import com.company.secondhand.user.dto.UpdateUserDetailsRequest;
import com.company.secondhand.user.dto.UserDetailsDto;
import com.company.secondhand.user.dto.converter.UserDetailsDtoConverter;
import com.company.secondhand.user.exception.UserDetailsNotFoundException;
import com.company.secondhand.user.exception.UserNotFoundException;
import com.company.secondhand.user.model.User;
import com.company.secondhand.user.model.UserDetails;
import com.company.secondhand.user.repository.UserDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserDetailsServiceTest extends TestSupport {

    private UserDetailsRepository userDetailsRepository;
    private UserService userService;
    private UserDetailsDtoConverter converter;

    private UserDetailsService userDetailsService;

    @BeforeEach
    public void setUp(){
        userDetailsRepository = mock(UserDetailsRepository.class);
        userService = mock(UserService.class);
        converter = mock(UserDetailsDtoConverter.class);

        userDetailsService = new UserDetailsService(userDetailsRepository,userService,converter);
    }

    @Test
    void testCreateUserDetails_whenUserIdExist_itShouldReturnCreatedUserDetailsDto(){
        CreateUserDetailsRequest request = generateCreateUserDetailsRequest(userId);
        User user = new User(userId,"parvin.valizade@mail.ru","Parvin","Valizade","Pempi",true);
        UserDetails userDetails = new UserDetails("94384874","Baku","Baku","Azerbaijan","000",user);
        UserDetails savedUserDetails = new UserDetails("94384874","Baku","Baku","Azerbaijan","000",user);
        UserDetailsDto userDetailsDto = new UserDetailsDto("94384874","Baku","Baku","Azerbaijan","000");

        when(userService.findUserById(request.getUserId())).thenReturn(user);
        when(userDetailsRepository.save(userDetails)).thenReturn(savedUserDetails);
        when(converter.convert(savedUserDetails)).thenReturn(userDetailsDto);

        UserDetailsDto result = userDetailsService.createUserDetails(request);

        assertEquals(userDetailsDto,result);

        verify(userService).findUserById(request.getUserId());
        verify(userDetailsRepository).save(userDetails);
        verify(converter).convert(savedUserDetails);

    }

    @Test
    void testCreateUserDetails_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException(){
        CreateUserDetailsRequest request = generateCreateUserDetailsRequest(userId);


        when(userService.findUserById(request.getUserId())).thenThrow(new UserNotFoundException("test exception"));

        assertThrows(UserNotFoundException.class,
                () -> userDetailsService.createUserDetails(request));

        verify(userService).findUserById(request.getUserId());
        verifyNoMoreInteractions(userService);
        verifyNoInteractions(converter);

    }

    @Test
    void testUpdateUserDetails_whenUserDetailsIdExist_itShouldReturnUpdatedUserDetailsDto(){
        UpdateUserDetailsRequest request = new UpdateUserDetailsRequest("94384874","Yasamal","Baku","Azerbaijan","000");
        User user = new User(userId,"parvin.valizade@mail.ru","Parvin","Valizade","Pempi",true);
        UserDetails userDetails = new UserDetails("94384874","Baku","Baku","Azerbaijan","000",user);
        UserDetails savedUserDetails = new UserDetails("94384874","Yasamal","Baku","Azerbaijan","000",user);
        UserDetails updateUserDetails = new UserDetails("94384874","Yasamal","Baku","Azerbaijan","000",user);
        UserDetailsDto userDetailsDto = new UserDetailsDto("94384874","Yasamal","Baku","Azerbaijan","000");

        when(userDetailsRepository.findById(userDetails.getId())).thenReturn(Optional.of(userDetails));
        when(userDetailsRepository.save(updateUserDetails)).thenReturn(savedUserDetails);
        when(converter.convert(savedUserDetails)).thenReturn(userDetailsDto);

        UserDetailsDto result = userDetailsService.updateUserDetails(userDetails.getId(),request);

        assertEquals(userDetailsDto,result);

        verify(userDetailsRepository).findById(userDetails.getId());
        verify(userDetailsRepository).save(updateUserDetails);
        verify(converter).convert(savedUserDetails);
    }

    @Test
    void testUpdateUserDetails_whenUserDetailsIdDoesNotExist_itShouldThrowUserDetailsNotFoundException(){
        UpdateUserDetailsRequest request = new UpdateUserDetailsRequest("94384874","Yasamal","Baku","Azerbaijan","000");

        when(userDetailsRepository.findById(userDetailsId)).thenReturn(Optional.empty());

        assertThrows(UserDetailsNotFoundException.class,
                ()->userDetailsService.updateUserDetails(userDetailsId,request));

        verify(userDetailsRepository).findById(userDetailsId);
        verifyNoMoreInteractions(userDetailsRepository);
        verifyNoInteractions(converter);
    }

    @Test
    void testDeleteUserDetails_whenUserDetailsIdExist_itShouldDeleteUserDetails(){

        User user = new User(userId,"parvin.valizade@mail.ru","Parvin","Valizade","Pempi",true);
        UserDetails userDetails = new UserDetails("94384874","Baku","Baku","Azerbaijan","000",user);

        when(userDetailsRepository.findById(userDetails.getId())).thenReturn(Optional.of(userDetails));

        userDetailsService.deleteUserDetails(userDetails.getId());

        verify(userDetailsRepository).findById(userDetails.getId());
        verify(userDetailsRepository).deleteById(userDetails.getId());

    }

    @Test
    void testDeleteUserDetails_whenUserDetailsIdDoesNotExist_itShouldThrowUserDetailsNotFoundException(){

        when(userDetailsRepository.findById(userDetailsId)).thenReturn(Optional.empty());

        assertThrows(UserDetailsNotFoundException.class,
                ()->userDetailsService.deleteUserDetails(userDetailsId));

        verify(userDetailsRepository).findById(userDetailsId);
        verifyNoMoreInteractions(userDetailsRepository);

    }



}
