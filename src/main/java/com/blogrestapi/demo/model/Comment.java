package com.blogrestapi.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "commentTable"
)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private long id ;
    private String name ;
    private String email ;
    private String body ;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "post_id", nullable = false)
    private Post post ;
}
