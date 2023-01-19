package com.blogrestapi.demo.payload;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private long id ;

    @NotEmpty
    @Size(min = 2, message = "Post Title should have atleast 2 characters")
    private String postTitle ;

    @NotEmpty
    @Size(min = 5, message = "Post desciption should have atleast 5 characters")
    private String postDesc ;

    @NotEmpty
    private String postContent ;
    private Set<CommentDto> comments ;
}
