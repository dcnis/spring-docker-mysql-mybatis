package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RedisHash(value = "Lesson", timeToLive = 7200) // 2 hours
public class Lesson implements Serializable {

    private Integer id;
    private String title;
    private String discussion;
    private Difficulty difficulty;
    private String thumbnailUrl;
    private String audioUrl;
    private List<Vocabulary> vocabulary;
}
