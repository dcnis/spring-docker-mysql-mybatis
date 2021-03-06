package de.schmidtdennis.mysqlspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.schmidtdennis.mysqlspring.constants.RedisKeys;
import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.AudioFile;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.repository.RedisAudioRepository;
import de.schmidtdennis.mysqlspring.repository.RedisLessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

@Service
@Slf4j
public class RedisService {

    private final LessonMapper lessonMapper;
    private final RedisLessonRepository redisLessonRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final ValueOperations<String, String> listOps;
    private final RedisAudioRepository redisAudioRepository;

    public RedisService(RedisLessonRepository redisLessonRepository,
                        RedisTemplate<String, Object> redisTemplate,
                        ObjectMapper objectMapper,
                        LessonMapper lessonMapper,
                        ValueOperations<String, String> listOps,
                        RedisAudioRepository redisAudioRepository){
        this.redisLessonRepository = redisLessonRepository;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
        this.lessonMapper = lessonMapper;
        this.listOps = listOps;
        this.redisAudioRepository = redisAudioRepository;
    }

    public TreeMap<Integer, Lesson> getAllSavedLessons() {
        return redisLessonRepository.getAllSavedLessons();
    }

    public Set<String> getAllKeys(){
        return redisTemplate.keys("*");
    }

    public void addAllLessons(List<Lesson> allLessons){

        try {
            String lessonsAsString = objectMapper.writeValueAsString(allLessons);
            listOps.set(RedisKeys.REDIS_KEY_ALL_LESSONS, lessonsAsString);
        } catch (JsonProcessingException e) {
            log.error("Fehler beim Parsen von allLessons as String", e);
            e.printStackTrace();
        }
    }

    public List<Lesson> getAllLessons(){

        String allLessonsAsString =  listOps.get(RedisKeys.REDIS_KEY_ALL_LESSONS);

        try {
            if(allLessonsAsString != null){
                return objectMapper.readValue(allLessonsAsString, new TypeReference<>() {});
            }
        } catch (JsonProcessingException e) {
            log.error("Fehler beim Parsen des allLessonString to ArrayList", e);
            e.printStackTrace();
        }

        return null;
    }

    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    public void refreshAllLessons(){
        List<Lesson> allLessons = lessonMapper.getAllLessons();

        if(allLessons != null && !allLessons.isEmpty()){
            this.addAllLessons(allLessons);
        }
    }

    public int updateSingleLessonIfNecessary(Integer lessonId){

        Lesson lesson = redisLessonRepository.findLesson(lessonId);

        if(lesson != null){

            Lesson updatedLesson = lessonMapper.getLesson(lessonId);

            log.info("Update lesson {} in Redis", lessonId);
            redisLessonRepository.saveLesson(updatedLesson);
            return 1;
        }

        return 0;
    }

    public void addAudio(Integer lessonId, MultipartFile file) {
        AudioFile audioFile = new AudioFile();
        audioFile.setLessonId(lessonId);
        audioFile.setFilename(file.getOriginalFilename());

        try {
            audioFile.setData(file.getBytes());
        } catch (IOException e) {
            log.error("Could not read audioFile", e);
            e.printStackTrace();
        }

        redisAudioRepository.saveAudio(audioFile);

    }

    public AudioFile getAudio(Integer lessonId) {
        return redisAudioRepository.findAudio(lessonId);
    }

    public void deleteAudio(Integer lessonId) {
        redisAudioRepository.deleteAudio(lessonId);
    }
}
