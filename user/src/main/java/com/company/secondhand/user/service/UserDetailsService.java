package com.company.secondhand.user.service;

import com.company.secondhand.user.dto.CreateUserDetailsRequest;
import com.company.secondhand.user.dto.UpdateUserDetailsRequest;
import com.company.secondhand.user.dto.UserDetailsDto;
import com.company.secondhand.user.dto.converter.UserDetailsDtoConverter;
import com.company.secondhand.user.exception.UserDetailsNotFoundException;
import com.company.secondhand.user.model.User;
import com.company.secondhand.user.model.UserDetails;
import com.company.secondhand.user.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserService userService;
    private final UserDetailsDtoConverter converter;

    public UserDetailsService(UserDetailsRepository userDetailsRepository, UserService userService, UserDetailsDtoConverter converter) {
        this.userDetailsRepository = userDetailsRepository;
        this.userService = userService;
        this.converter = converter;
    }

    public UserDetailsDto createUserDetails(final CreateUserDetailsRequest request){
        User user = userService.findUserById(request.getUserId());

        UserDetails userDetails = new UserDetails(
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getPostCode(),
                user
        );
        return converter.convert(userDetailsRepository.save(userDetails));
    }

    public UserDetailsDto updateUserDetails(final Long userDetailsId,final UpdateUserDetailsRequest request){
        UserDetails userDetails = findUserDetailsById(userDetailsId);

        UserDetails updateUserDetails = new UserDetails(
                userDetails.getId(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                request.getPostCode(),
                userDetails.getUser()
        );
        return converter.convert(userDetailsRepository.save(updateUserDetails));
    }

    public void deleteUserDetails(final Long id){
        findUserDetailsById(id);
        userDetailsRepository.deleteById(id);
    }

    private UserDetails findUserDetailsById(final Long userDetailsId){
        return userDetailsRepository.findById(userDetailsId)
                .orElseThrow(() -> new UserDetailsNotFoundException("User details couldn't be found by following id: " + userDetailsId));
    }


}
