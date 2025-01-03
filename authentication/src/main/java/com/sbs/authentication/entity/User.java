package com.sbs.authentication.entity;

import com.sbs.authentication.dto.UserDto;
import com.sbs.authentication.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String lastName;

    private String mobileNo;

    private UserRole role;

    public UserDto getDto(){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setRole(role);
        userDto.setLastname(lastName);
        userDto.setPhone(mobileNo);
        userDto.setPassword(password);
        return userDto;
    }
}
