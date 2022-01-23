package de.schmidtdennis.mysqlspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.schmidtdennis.mysqlspring.constants.RedisKeys;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.repository.RedisLessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

@Service
@Slf4j
public class RedisService {

    private final RedisLessonRepository redisLessonRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> listOps;

    public RedisService(RedisLessonRepository redisLessonRepository, RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper){
        this.redisLessonRepository = redisLessonRepository;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
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
            listOps.set(RedisKeys.REDIS_ALL_LESSONS, lessonsAsString);
        } catch (JsonProcessingException e) {
            log.error("Fehler beim Parsen von allLessons as String", e);
            e.printStackTrace();
        }


    }

    public List<Lesson> getAllLessons(){

        String allLessonsAsString =  listOps.get(RedisKeys.REDIS_ALL_LESSONS);

        try {
            return objectMapper.readValue(allLessonsAsString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            log.error("Fehler beim Parsen des allLessonString to ArrayList", e);
            e.printStackTrace();
        }

        return null;
    }

    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

}
