package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotNull;

@RedisHash("Audio")
@Getter
@Setter
public class AudioFile {

    @NotNull
    private Integer lessonId;

    @NotNull
    private String filename;

    @NotNull
    private byte[] data;

}
