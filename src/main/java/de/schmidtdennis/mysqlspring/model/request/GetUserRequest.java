package de.schmidtdennis.mysqlspring.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRequest {
    private Integer userId;
    private String userEmail;
}
