package com.sbs.authentication.dto;

import lombok.Data;

@Data
public class SignUpRequestDto {

    private String email;

    private String password;

    private String name;

    private String lastname;

    private String phone;
}
