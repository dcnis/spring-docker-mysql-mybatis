package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ExceptionControllerAdviceTest {

    @InjectMocks
    private ExceptionControllerAdvice testee;

    @Test
    public void test(){
        // GIVEN

        // WHEN
        ResponseEntity<ErrorResponse> response = testee.exceptionHandler(new IllegalArgumentException("fail"));

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getError()).isEqualTo("fail");
    }

}