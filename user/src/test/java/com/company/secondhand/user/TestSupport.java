package com.company.secondhand.user;

import com.company.secondhand.user.dto.CreateUserDetailsRequest;
import com.company.secondhand.user.dto.UserDetailsDto;
import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    public static Long userId = 100L;
    public static Long userDetailsId = 1L;

    public static List<User> generateUsers() {
        return IntStream.range(0, 5).mapToObj(i ->
                new User((long) i,
                        i + "test@gmail.com",
                        "fistName" + i,
                        "lastName" + i,
                        "",
                        new Random(2).nextBoolean(),
                        new HashSet<>())).collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<User> userList){
        return userList.stream()
                .map(from -> new UserDto(
                        from.getMail(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getMiddleName(),
                        new ArrayList<>()
                )).collect(Collectors.toList());
    }

    public static User generateUser(String mail) {
        return new User(userId,
                mail,
                "fistName" + userId,
                "lastName" + userId,
                "",
                true);
    }

    public static UserDto generateUserDto(String mail) {
        return new UserDto(mail,
                "fistName" + userId,
                "lastName" + userId,
                "",
                new ArrayList<>());
    }

    public static CreateUserDetailsRequest generateCreateUserDetailsRequest(Long userId){
        return new CreateUserDetailsRequest("94384874","Baku","Baku","Azerbaijan","000",userId);
    }
}
