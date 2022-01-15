package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.UserLessonMapper;
import de.schmidtdennis.mysqlspring.model.UserLesson;
import de.schmidtdennis.mysqlspring.model.response.UserLessonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserLessonService {

    private final UserLessonMapper userLessonMapper;

    @Autowired
    public UserLessonService(UserLessonMapper userLessonMapper){
        this.userLessonMapper = userLessonMapper;
    }

    public UserLessonResult getUserLessons(Integer userId, String email){

        if(userId != null){
            List<UserLesson> userLessons = userLessonMapper.getUserLessonsByUserId(userId);
            return new UserLessonResult(userLessons.size(), userLessons);
        } else if(email != null){
            List<UserLesson> userLessons = userLessonMapper.getUserLessonByEmail(email);
            return new UserLessonResult(userLessons.size(), userLessons);
        }

        return null;
    }
}
