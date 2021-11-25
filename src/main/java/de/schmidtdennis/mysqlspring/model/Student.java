package de.schmidtdennis.mysqlspring.model;

import de.schmidtdennis.mysqlspring.enums.Gender;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Student")
public class Student implements Serializable {

    private String id;
    private String name;
    private Gender gender;
    private int age;

}
