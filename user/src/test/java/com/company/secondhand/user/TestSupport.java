package com.company.secondhand.user;

import com.company.secondhand.user.dto.UserDto;
import com.company.secondhand.user.model.User;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
    public static Long userId = 100L;

    public static List<User> generateUsers() {
        return IntStream.range(0, 5).mapToObj(i ->
                new User((long) i,
                        i + "test@gmail.com",
                        "fistName" + i,
                        "lastName" + i,
                        "",
                        new Random(2).nextBoolean())).collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<User> userList){
        return userList.stream()
                .map(from -> new UserDto(
                        from.getMail(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getMiddleName()
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
                "");
    }
}
