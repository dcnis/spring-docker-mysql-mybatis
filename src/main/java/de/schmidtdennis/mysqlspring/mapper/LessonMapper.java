package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.Difficulty;
import de.schmidtdennis.mysqlspring.model.Lesson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LessonMapper {

    List<Lesson> getAllLessons();

    List<Lesson> getLessonByDifficulty(Integer difficultyId);

    Lesson getLesson(Integer lessonId);

    Difficulty getDifficulty(Integer difficultyId);

    int addLesson(Lesson lesson);

}


