package com.company.secondhand.user.dto.converter;

import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDto> convert(List<User> fromList){
        return fromList.stream()
                .map(from -> new UserDto(
                        from.getMail(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getMiddleName()
                )).collect(Collectors.toList());
    }
}
