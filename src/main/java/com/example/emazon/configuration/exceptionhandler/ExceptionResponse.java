package com.example.emazon.configuration.exceptionhandler;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private HttpStatus statusCode;
    private String message;
    private LocalDateTime timestamp;
}
