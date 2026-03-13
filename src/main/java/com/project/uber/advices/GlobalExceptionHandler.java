package com.project.uber.advices;

import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.exceptions.RuntimeConflictException;
import io.jsonwebtoken.JwtException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeConflictsException(RuntimeConflictException e){
        ApiError error= ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();
        return buildErrorResponseEntity(error);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException e){
        ApiError error = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getLocalizedMessage())
                .build();
        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception e){
        ApiError error = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getLocalizedMessage())
                .build();
        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException e){
        List<String> errors = e
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ex -> ex.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError error = ApiError.builder()
                .subErrors(errors)
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getLocalizedMessage())
                .build();
        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException ex){
        ApiError error = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(ex.getLocalizedMessage())
                .build();
        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException ex){
        ApiError apiError = ApiError.builder()
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleExcessDeniedException(AccessDeniedException ex){
        ApiError apiError = ApiError.builder()
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.FORBIDDEN)
                .build();
        return buildErrorResponseEntity(apiError);
    }

}
