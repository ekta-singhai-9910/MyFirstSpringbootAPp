package com.blogrestapi.demo.service;

import com.blogrestapi.demo.payload.LoginDto;
import com.blogrestapi.demo.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto) ;

    String register(RegisterDto registerDto) ;
}
