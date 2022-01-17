package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash("Difficulty")
@NoArgsConstructor
public class Difficulty implements Serializable {

    private Integer id;
    private String description;

    public Difficulty(Integer id, String description){
        this.id = id;
        this.description = description;
    }

}
