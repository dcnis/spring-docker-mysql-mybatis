package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.TreeMap;

@RestController
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService){
        this.redisService = redisService;
    }

    @GetMapping("redis/lessons")
    public TreeMap<Integer, Lesson> getAllSavedLessons(){
        return redisService.getAllSavedLessons();
    }

    @GetMapping("redis/keys")
    public Set<String> getAllKeys(){
        return redisService.getAllKeys();
    }

}
