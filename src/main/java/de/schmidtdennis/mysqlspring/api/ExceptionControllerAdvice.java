package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(IllegalArgumentException e) {
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
