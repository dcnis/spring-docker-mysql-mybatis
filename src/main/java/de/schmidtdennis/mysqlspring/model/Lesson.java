package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash(value = "Lesson", timeToLive = 7200) // 2 hours
public class Lesson implements Serializable {

    private Integer id;
    private String title;
    private String discussion;
    private Difficulty difficulty;
    private String thumbnailUrl;
    private String audioUrl;

}
