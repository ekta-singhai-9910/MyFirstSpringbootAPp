package com.blogrestapi.demo.service.impl;

import com.blogrestapi.demo.exception.BlogApiException;
import com.blogrestapi.demo.exception.ResourceNotFoundException;
import com.blogrestapi.demo.model.Comment;
import com.blogrestapi.demo.model.Post;
import com.blogrestapi.demo.payload.CommentDto;
import com.blogrestapi.demo.repository.CommentDataRepository;
import com.blogrestapi.demo.repository.PostDataRepository;
import com.blogrestapi.demo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDataRepository commentDataRepository ;

    @Autowired
    PostDataRepository postDataRepository ;

    private ModelMapper mapper ;


    public CommentServiceImpl(CommentDataRepository commentDataRepository, ModelMapper mapper){
        this.commentDataRepository = commentDataRepository ;
        this.mapper = mapper ;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto){
        Comment comment = mapToEntity(commentDto) ;

        Post post = postDataRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId)) ;
        comment.setPost(post);
        Comment newComment = commentDataRepository.save(comment) ;

        return mapToDto(newComment) ;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentDataRepository.findByPostId(postId) ;
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postDataRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId)) ;

        Comment comment = commentDataRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "commentId", commentId)) ;

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post") ;
        }
        return mapToDto(comment) ;
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post post = postDataRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId)) ;

        Comment comment = commentDataRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "commentId", commentId)) ;

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post") ;
        }

        comment.setBody(commentDto.getBody());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());

        Comment updatedComment = commentDataRepository.save(comment) ;
        return mapToDto(updatedComment) ;
    }


    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class) ;
//        CommentDto commentDto = new CommentDto() ;
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setName(comment.getName());
        return commentDto ;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class) ;
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
        return comment;
    }
}
