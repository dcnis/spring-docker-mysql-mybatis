package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.repository.RedisLessonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

@RestController
public class RedisController {

    private final RedisLessonRepository redisLessonRepository;

    public RedisController(RedisLessonRepository redisLessonRepository){
        this.redisLessonRepository = redisLessonRepository;
    }

    @GetMapping("redis/lessons")
    public TreeMap<Integer, Lesson> getAllSavedLessons(){
        return redisLessonRepository.getAllSavedLessons();
    }


}
