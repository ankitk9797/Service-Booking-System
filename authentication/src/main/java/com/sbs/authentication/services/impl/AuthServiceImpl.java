package com.sbs.authentication.services.impl;

import com.sbs.authentication.dto.SignUpRequestDto;
import com.sbs.authentication.dto.UserDto;
import com.sbs.authentication.entity.User;
import com.sbs.authentication.enums.UserRole;
import com.sbs.authentication.repository.UserRepository;
import com.sbs.authentication.services.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDto signupClient(SignUpRequestDto dto) {
        UserDto userDto = findFirstByEmail(dto.getEmail());
        if (userDto != null){
            return null;
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setLastName(dto.getLastname());
        user.setMobileNo(dto.getMobileNo());
        user.setRole(UserRole.CLIENT);
        userRepository.save(user);
        return user.getDto();
    }

    @Transactional
    @Override
    public UserDto signupCompany(SignUpRequestDto dto) {
        UserDto userDto = findFirstByEmail(dto.getEmail());
        if (userDto != null){
            return null;
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setLastName(dto.getLastname());
        user.setMobileNo(dto.getMobileNo());
        user.setRole(UserRole.COMPANY);
        userRepository.save(user);
        return user.getDto();
    }

    @Override
    public UserDto getUser(long userId) {
        Optional<User> optional = userRepository.findById(userId);
        if(optional.isPresent()){
            return optional.get().getDto();
        }
        return null;
    }

    @Override
    public UserDto findFirstByEmail(String email) {
        User user = userRepository.findFirstByEmail(email);
        if(user==null){
            return  null;
        }
        return userRepository.findFirstByEmail(email).getDto();
    }
}
