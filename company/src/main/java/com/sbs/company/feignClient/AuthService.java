package com.sbs.company.feignClient;

import com.sbs.company.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AUTHENTICATION")
public interface AuthService {

    @GetMapping(path = "/userById/{userId}")
    Boolean getUser(@PathVariable long userId);

    @GetMapping(path = "/userByEmail/{email}")
    UserDto findUserByEmail(@PathVariable String email);
}
