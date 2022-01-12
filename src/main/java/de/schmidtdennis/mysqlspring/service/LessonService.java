package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.exceptions.LessonNotFoundException;
import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.repository.RedisLessonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private RedisLessonRepository redisLessonRepository;

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
}
