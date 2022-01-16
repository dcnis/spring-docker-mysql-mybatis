package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.UserLessonMapper;
import de.schmidtdennis.mysqlspring.model.UserLesson;
import de.schmidtdennis.mysqlspring.model.request.AddUserLesson;
import de.schmidtdennis.mysqlspring.model.response.UserLessonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class UserLessonService {

    private final UserLessonMapper userLessonMapper;

    @Autowired
    public UserLessonService(UserLessonMapper userLessonMapper) {
        this.userLessonMapper = userLessonMapper;
    }

    public UserLessonResult getUserLessons(Integer userId, String email) {

        if (userId != null) {
            List<UserLesson> userLessons = userLessonMapper.getUserLessonsByUserId(userId);
            return new UserLessonResult(userLessons.size(), userLessons);
        } else if (email != null) {
            List<UserLesson> userLessons = userLessonMapper.getUserLessonsByEmail(email);
            return new UserLessonResult(userLessons.size(), userLessons);
        }

        return null;
    }

    public int addUserLesson(AddUserLesson request) {

        UserLesson userLesson = userLessonMapper.getSpecificUserLesson(request.getUserId(), request.getLessonId());

        if (userLesson != null) {
            log.debug("Update Timestamp for UserLesson {}-{}", request.getUserId(), request.getLessonId());
            return userLessonMapper.updateUserLessonTimestamp(request.getUserId(), request.getLessonId());
        } else {
            log.debug("Add UserLesson {}-{}", request.getUserId(), request.getLessonId());
            return userLessonMapper.addUserLesson(request.getUserId(), request.getLessonId());
        }
    }
}
