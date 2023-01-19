package com.blogrestapi.demo.service.impl;

import com.blogrestapi.demo.exception.BlogApiException;
import com.blogrestapi.demo.model.Role;
import com.blogrestapi.demo.model.User;
import com.blogrestapi.demo.payload.LoginDto;
import com.blogrestapi.demo.payload.RegisterDto;
import com.blogrestapi.demo.repository.RoleDataRepository;
import com.blogrestapi.demo.repository.UserDataRepository;
import com.blogrestapi.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager ;

    @Autowired
    private UserDataRepository userDataRepository ;

    @Autowired
    private RoleDataRepository roleDataRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
                loginDto.getPassword())) ;

        SecurityContextHolder.getContext().setAuthentication(authentication) ;

        return "User logged in successfully!";
    }

    @Override
    public String register(RegisterDto registerDto) {

        if(userDataRepository.existsByUsername(registerDto.getUsername()) ){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username already exists") ;
        }

        if(userDataRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "User for this email already exists") ;
        }

        User user = new User() ;
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setName(registerDto.getName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role>roles = new HashSet<>() ;
        Role userRole = roleDataRepository.findByName("ROLE_USER").get()  ;
        roles.add(userRole) ;
        user.setRoles(roles);
        userDataRepository.save(user) ;

        return "User registered successfully" ;
    }
}
