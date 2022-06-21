package com.company.secondhand.user.dto.converter;

import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return new UserDto(
                from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                from.getMiddleName()
                );
    }
}
