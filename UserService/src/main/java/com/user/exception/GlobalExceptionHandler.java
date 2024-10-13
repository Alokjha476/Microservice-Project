package com.user.exception;

import com.user.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // TODO -> full project centralized exception Handler

public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {

        String message = ex.getMessage();

        ApiResponse response = ApiResponse.builder().
                message(message).success(true).status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HotelIdNotFoundException.class)
    public ResponseEntity<ApiResponse> handleHotelIdNotFoundException(HotelIdNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(msg).
                success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

    }
}
