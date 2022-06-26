package com.company.secondhand.user.dto.converter;

import com.company.secondhand.user.dto.UserDetailsDto;
import com.company.secondhand.user.model.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {

    public UserDetailsDto convert(UserDetails from){
        return new UserDetailsDto(
                from.getPhoneNumber(),
                from.getAddress(),
                from.getCity(),
                from.getCity(),
                from.getPostCode());
    }

    public List<UserDetailsDto> convert(List<UserDetails> from){
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
