package de.schmidtdennis.mysqlspring.model;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.lang.reflect.Field;

@Getter
@Setter
@NoArgsConstructor
@RedisHash(value = "User", timeToLive = 3600)
@FieldNameConstants
public class User implements Serializable {

    public User(Integer id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;


    public boolean atLeastOneFieldToBeUpdated() throws IllegalAccessException {
        for (Field field : getClass().getDeclaredFields())
            if (!field.getName().equals("id") && field.get(this) != null)
                return true;
        return false;
    }

}
