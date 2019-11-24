package com.iljun.hateoasexample.domains.user.dto.request;

import com.iljun.hateoasexample.domains.user.User;
import lombok.Data;

@Data
public class UserRequestDto {

    private long userId;

    private String username;

    private String email;

    private String phoneNumber;

    public static User of(UserRequestDto userRequestDto) {
        return User
                .builder()
                .id(userRequestDto.userId)
                .email(userRequestDto.email)
                .phoneNumber(userRequestDto.phoneNumber)
                .username(userRequestDto.username)
                .build();
    }
}
