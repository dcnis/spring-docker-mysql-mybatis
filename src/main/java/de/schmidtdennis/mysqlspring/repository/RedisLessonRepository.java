package de.schmidtdennis.mysqlspring.repository;

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
    public static String LESSON_PREFIX = "Lesson:";

    @Autowired
    public RedisLessonRepository(HashOperations<String, Integer, Lesson> hashOperationsLesson){
        this.hashOperationsLesson = hashOperationsLesson;
    }

    public void saveLesson(Lesson lesson){
        hashOperationsLesson.put(LESSON_PREFIX, lesson.getId(), lesson);
    }

    public void updateLesson(Lesson lesson){
        hashOperationsLesson.put(LESSON_PREFIX, lesson.getId(), lesson);
    }

    public Lesson findLesson(int lessonId){
        return hashOperationsLesson.get(LESSON_PREFIX, lessonId);
    }

    public void deleteLesson(int lessonId){
        hashOperationsLesson.delete(LESSON_PREFIX, lessonId);
    }

    public TreeMap<Integer, Lesson> getAllSavedLessons(){
        return new TreeMap<>(hashOperationsLesson.entries(LESSON_PREFIX));
    }

}
