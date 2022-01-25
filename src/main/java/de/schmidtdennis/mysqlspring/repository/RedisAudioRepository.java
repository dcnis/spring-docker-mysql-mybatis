package de.schmidtdennis.mysqlspring.repository;

import de.schmidtdennis.mysqlspring.constants.RedisKeys;
import de.schmidtdennis.mysqlspring.model.AudioFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;

@Repository
@Slf4j
public class RedisAudioRepository {

    private final HashOperations<String, Integer, AudioFile> hashOperations;

    @Autowired
    public RedisAudioRepository(HashOperations<String, Integer, AudioFile> hashOperations){
        this.hashOperations = hashOperations;
    }

    public void saveAudio(@Valid AudioFile audioFile){
        hashOperations.put(RedisKeys.REDIS_KEY_AUDIO, audioFile.getLessonId(), audioFile);
        log.debug("added Audio:{} '{}' to REDIS", audioFile.getLessonId(), audioFile.getFilename());
    }

    public AudioFile findAudio(Integer lessonId){

        AudioFile audioFile = hashOperations.get(RedisKeys.REDIS_KEY_AUDIO, lessonId);

        if(audioFile == null){
            log.debug("Audio:{} does not exist in REDIS", lessonId);
            return null;
        }

        log.debug("return Audio:{} from REDIS", lessonId);
        return audioFile;
    }

    public void deleteAudio(Integer lessonId){
        log.debug("delete Audio:{} in REDIS", lessonId);
        hashOperations.delete(RedisKeys.REDIS_KEY_AUDIO, lessonId);
    }


}
