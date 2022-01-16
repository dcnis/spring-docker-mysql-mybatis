package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.UserLesson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLessonMapper {

    List<UserLesson> getUserLessonsByUserId(Integer userId);

    List<UserLesson> getUserLessonsByEmail(String email);

    int addUserLesson(Integer userId, Integer lessonId);

    UserLesson getSpecificUserLesson(Integer userId, Integer lessonId);

    int updateUserLessonTimestamp(Integer userId, Integer lessonId);
}
