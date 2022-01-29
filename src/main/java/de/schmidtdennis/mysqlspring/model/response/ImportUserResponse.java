package de.schmidtdennis.mysqlspring.model.response;

import de.schmidtdennis.mysqlspring.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImportUserResponse {

    private int count;
    private List<User> users;

}
