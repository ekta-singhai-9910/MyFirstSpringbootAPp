package com.blogrestapi.demo.model;


import lombok.*;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "posttable"
)
public class Post {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id ;

    @Column(name = "PostTitle", nullable = false)
    private String postTitle ;

    @Column(name = "PostDescription", nullable = false)
    private String postDesc ;

    @Column(name = "PostContent", nullable = false)
    private String postContent ;


    @OneToMany( mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment>comments = new HashSet<>() ;

}
