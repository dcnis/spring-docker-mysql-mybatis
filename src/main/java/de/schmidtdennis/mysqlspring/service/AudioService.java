package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.model.AudioFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AudioService {

    private final RedisService redisService;

    @Autowired
    public AudioService(RedisService redisService){
        this.redisService = redisService;
    }

    public void addAudio(Integer lessonId, MultipartFile file){
        redisService.addAudio(lessonId, file);
    }

    public AudioFile getAudio(Integer lessonId){
        return redisService.getAudio(lessonId);
    }

    public void deleteAudio(Integer lessonId) {
        redisService.deleteAudio(lessonId);
    }
}
