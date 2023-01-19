package com.blogrestapi.demo.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {
    private String name ;

    @NotEmpty
    @Size(min = 2, message = "username should be greater than two charcters")
    private String username;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 2, message = "Password should be greater than two charcters")
    private String password ;
}
