package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.Difficulty;
import de.schmidtdennis.mysqlspring.model.Lesson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LessonMapper {

    @Select("SELECT * FROM Lessons")
    @Results(id = "lessonResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "discussion", column = "discussion"),
            @Result(property = "difficulty", column = "difficulty", javaType = Difficulty.class, one=@One(select="getDifficulty")),
            @Result(property = "thumbnailUrl", column = "thumbnail_url"),
            @Result(property = "audioUrl", column = "audio_url")
    })
    List<Lesson> getAllLessons();


    @Select("SELECT * FROM Difficulties WHERE id=#{difficultyId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "description", column = "description")
    })
    Difficulty getDifficulty(String difficultyId);

    @Select("SELECT * FROM Lessons WHERE id=#{lessonId}")
    @ResultMap("lessonResult")
    Lesson getLesson(Integer lessonId);
}


