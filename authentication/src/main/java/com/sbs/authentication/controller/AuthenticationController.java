package com.sbs.authentication.controller;

import com.sbs.authentication.dto.AuthenticationRequest;
import com.sbs.authentication.dto.SignUpRequestDto;
import com.sbs.authentication.dto.UserDto;
import com.sbs.authentication.entity.User;
import org.json.JSONException;
import com.sbs.authentication.repository.UserRepository;
import com.sbs.authentication.services.AuthService;
import com.sbs.authentication.services.jwt.UserDetailsServiceImpl;
import com.sbs.authentication.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    @PostMapping(path = "signup/client")
    public ResponseEntity<?> signUpClient(@RequestBody SignUpRequestDto dto) {
        UserDto userDto = authService.signupClient(dto);
        if(userDto==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("User with email " + dto.getEmail() + " already exists");
        }
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping(path = "signup/company")
    public ResponseEntity<?> signUpCompany(@RequestBody SignUpRequestDto dto) {
        UserDto userDto = authService.signupCompany(dto);
        if(userDto==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body("User with email " + dto.getEmail() + " already exists");
        }
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping(path = "/userById/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId) {
        UserDto userDto = authService.getUser(userId);
//        if(userDto==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this userID not exists");
//        }
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping(path = "/userByEmail/{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable String email) {
        UserDto userDto = authService.findFirstByEmail(email);
//        if(userDto==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this userID not exists");
//        }
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping({"/authenticate"})
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                          HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());

        response.getWriter().write(new JSONObject()
                .put("userId", user.getId())
                .put("role", user.getRole())
                        .put("token", jwt)
                .toString()
        );

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization," +
                " X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);
    }

}
