package de.schmidtdennis.mysqlspring.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    public ErrorResponse(HttpStatus status, String error){
        this.status = status;
        this.error = error;
    }

    private LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatus status;
    private String error;
}
