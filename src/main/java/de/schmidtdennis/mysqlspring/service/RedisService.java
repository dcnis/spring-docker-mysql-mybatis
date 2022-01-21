package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.repository.RedisLessonRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeMap;

@Service
public class RedisService {

    private final RedisLessonRepository redisLessonRepository;

    private final RedisTemplate redisTemplate;

    public RedisService(RedisLessonRepository redisLessonRepository, RedisTemplate<String, Object> redisTemplate){
        this.redisLessonRepository = redisLessonRepository;
        this.redisTemplate = redisTemplate;
    }

    public TreeMap<Integer, Lesson> getAllSavedLessons() {
        return redisLessonRepository.getAllSavedLessons();
    }

    public Set<String> getAllKeys(){
        return redisTemplate.keys("*");
    }

}
