package com.blogrestapi.demo.service.impl;

import com.blogrestapi.demo.exception.ResourceNotFoundException;
import com.blogrestapi.demo.model.Post;
import com.blogrestapi.demo.payload.PostDto;
import com.blogrestapi.demo.payload.PostResponse;
import com.blogrestapi.demo.repository.PostDataRepository;
import com.blogrestapi.demo.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDataRepository postDataRepository ;

    private ModelMapper mapper ;

    PostServiceImpl(PostDataRepository postDataRepository, ModelMapper mapper){
        this.postDataRepository = postDataRepository ;
        this.mapper = mapper ;
    }

    @Override
    public PostDto createPost(PostDto postDto){

        //convert DTO to entity
        Post post = mapToEntity(postDto) ;

        //save entity to database
        Post newPost = postDataRepository.save(post) ;

        //convert saved entity to DTO
        PostDto postResponse = mapToDto(newPost) ;
        return postResponse;

    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending() ;
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort) ;
        Page<Post> posts = postDataRepository.findAll(pageable) ;

        PostResponse postResponse = new PostResponse() ;
        postResponse.setContent(posts.getContent());
        postResponse.setLast(posts.isLast());
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        return postResponse ;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postDataRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("post", "id", id)) ;
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto , long id) {
        Post post = postDataRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("post", "id", id)) ;
        post.setPostTitle(postDto.getPostTitle());
        post.setPostDesc(postDto.getPostDesc());
        post.setPostContent(postDto.getPostContent());
        Post updatedPost = postDataRepository.save(post) ;
        return mapToDto(updatedPost) ;
    }

    @Override
    public void deletePostById(long id) {
        Post post = postDataRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("post", "id", id)) ;
        postDataRepository.delete(post);
    }

    private Post mapToEntity(PostDto postDto){
         Post post = mapper.map(postDto, Post.class) ;
//        Post post = new Post();
//        post.setPostTitle(postDto.getPostTitle());
//        post.setPostContent(postDto.getPostContent());
//        post.setPostDesc(postDto.getPostDesc());
        return post ;
    }

    private PostDto mapToDto(Post newPost){
        PostDto postResponse = mapper.map(newPost, PostDto.class) ;
//        PostDto postResponse = new PostDto() ;
//        postResponse.setId(newPost.getId());
//        postResponse.setPostTitle(newPost.getPostTitle());
//        postResponse.setPostContent(newPost.getPostContent());
//        postResponse.setPostDesc(newPost.getPostDesc());
        return postResponse;
    }



}
