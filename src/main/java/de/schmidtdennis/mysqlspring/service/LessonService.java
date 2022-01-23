package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.constants.RedisKeys;
import de.schmidtdennis.mysqlspring.exceptions.LessonNotFoundException;
import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.model.response.AllLessonsResponse;
import de.schmidtdennis.mysqlspring.repository.RedisLessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LessonService {

    private final LessonMapper lessonMapper;
    private final RedisLessonRepository redisLessonRepository;
    private final RedisService redisService;


    @Autowired
    public LessonService(LessonMapper lessonMapper, RedisLessonRepository redisLessonRepository, RedisService redisService){
        this.lessonMapper = lessonMapper;
        this.redisLessonRepository = redisLessonRepository;
        this.redisService = redisService;
    }


    public Lesson getLesson(Integer lessonId){

        Lesson lesson = redisLessonRepository.findLesson(lessonId);
        if(lesson != null){
            log.info("Return lesson {} from Redis", lessonId);
            return lesson;
        }

        log.info("Get lesson {} from DB", lessonId);
        Lesson lessonFromDB = lessonMapper.getLesson(lessonId);

        if(lessonFromDB == null){
            throw new LessonNotFoundException(lessonId);
        }

        log.info("Save Lesson {} to REDIS", lessonId);
        redisLessonRepository.saveLesson(lessonFromDB);
        return lessonFromDB;
    }

    public AllLessonsResponse getLessonByDifficulty(Integer difficultyId) {

        List<Lesson> lessons =  lessonMapper.getLessonByDifficulty(difficultyId);
        return new AllLessonsResponse(lessons.size(), lessons);
    }

    public AllLessonsResponse getAllLessons() {

        List<Lesson> allLessons;

        if(!redisService.hasKey(RedisKeys.REDIS_ALL_LESSONS)){
            log.debug("Get allLessons from MySQL DB");
            allLessons = lessonMapper.getAllLessons();

            log.debug("Save allLessons to REDIS");
            redisService.addAllLessons(allLessons);

            return new AllLessonsResponse(allLessons.size(), allLessons);
        } else {
            // return from REDIS
            log.debug("Return allLessons from REDIS");
            allLessons = redisService.getAllLessons();

            return new AllLessonsResponse(allLessons.size(), allLessons);
        }
    }

    public Integer addLesson(Lesson lesson) {

        Integer newLessonId = lessonMapper.addLesson(lesson);

        List<Lesson> lessons = redisService.getAllLessons();

        if(lessons != null){
            lessons.add(lesson);
            redisService.addAllLessons(lessons);
            log.debug("Refreshed key {} with new lesson", RedisKeys.REDIS_ALL_LESSONS);
        }

        return newLessonId;
    }
}
