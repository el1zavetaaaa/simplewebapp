package com.mastery.java.task.exception.handling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
}
