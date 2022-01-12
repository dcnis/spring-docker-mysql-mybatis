package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash("Difficulty")
public class Difficulty implements Serializable {
    private Integer id;
    private String description;
}
