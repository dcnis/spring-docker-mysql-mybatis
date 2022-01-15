package de.schmidtdennis.mysqlspring.mapper;

import de.schmidtdennis.mysqlspring.model.UserLesson;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLessonMapper {

    List<UserLesson> getUserLessonsByUserId(Integer userId);

    List<UserLesson> getUserLessonByEmail(String userId);

}
