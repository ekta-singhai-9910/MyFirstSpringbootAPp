package com.blogrestapi.demo.exception;

import com.blogrestapi.demo.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                webRequest.getDescription(false), exception.getMessage()) ;
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handleBlogApiException(BlogApiException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                webRequest.getDescription(false), exception.getMessage()) ;
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                webRequest.getDescription(false), exception.getMessage()) ;
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                webRequest.getDescription(false), exception.getMessage()) ;
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED) ;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>() ;
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField() ;
            String message = error.getDefaultMessage() ;
            errors.put(fieldName, message) ;
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST) ;
    }


}
