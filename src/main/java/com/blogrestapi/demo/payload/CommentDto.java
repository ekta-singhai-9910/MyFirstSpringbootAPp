package com.blogrestapi.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long id ;

    @NotEmpty
    @Size(min = 2,message = "Post Title should have atleast 2 characters")
    private String name ;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Post Title should have atleast 10 characters")
    private String body ;
}
