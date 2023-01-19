package com.blogrestapi.demo.payload;

import com.blogrestapi.demo.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<Post> Content;
    private int pageNo;
    private int pageSize ;
    private boolean last;
    private long totalElements;
    private int totalPages ;
}
