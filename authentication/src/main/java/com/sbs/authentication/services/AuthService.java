package com.sbs.authentication.services;

import com.sbs.authentication.dto.SignUpRequestDto;
import com.sbs.authentication.dto.UserDto;

public interface AuthService {
    UserDto signupClient(SignUpRequestDto dto);

    UserDto signupCompany(SignUpRequestDto dto);

    UserDto getUser(long userId);

    UserDto findFirstByEmail(String email);
}
