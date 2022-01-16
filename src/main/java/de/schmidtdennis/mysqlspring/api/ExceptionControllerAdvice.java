package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.exceptions.LessonNotFoundException;
import de.schmidtdennis.mysqlspring.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(IllegalArgumentException e) {
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({LessonNotFoundException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(LessonNotFoundException e) {
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({org.springframework.validation.BindException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(BindException e) {
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(SQLException e) {
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
