package de.schmidtdennis.mysqlspring.repository;

import de.schmidtdennis.mysqlspring.constants.RedisKeys;
import de.schmidtdennis.mysqlspring.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import java.util.TreeMap;


@Repository
@Slf4j
public class RedisLessonRepository {

    private final HashOperations<String, Integer, Lesson> hashOperationsLesson;

    @Autowired
    public RedisLessonRepository(HashOperations<String, Integer, Lesson> hashOperationsLesson){
        this.hashOperationsLesson = hashOperationsLesson;
    }

    public void saveLesson(Lesson lesson){
        hashOperationsLesson.put(RedisKeys.REDIS_KEY_LESSON, lesson.getId(), lesson);
    }

    public void updateLesson(Lesson lesson){
        hashOperationsLesson.put(RedisKeys.REDIS_KEY_LESSON, lesson.getId(), lesson);
    }

    public Lesson findLesson(int lessonId){
        return hashOperationsLesson.get(RedisKeys.REDIS_KEY_LESSON, lessonId);
    }

    public void deleteLesson(int lessonId){
        hashOperationsLesson.delete(RedisKeys.REDIS_KEY_LESSON, lessonId);
    }

    public TreeMap<Integer, Lesson> getAllSavedLessons(){
        return new TreeMap<>(hashOperationsLesson.entries(RedisKeys.REDIS_KEY_LESSON));
    }

}
