package de.schmidtdennis.mysqlspring.model;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;

@Getter
@Setter
@NoArgsConstructor
@RedisHash(value = "User", timeToLive = 300)
public class User implements Serializable {

    public User(Integer id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
