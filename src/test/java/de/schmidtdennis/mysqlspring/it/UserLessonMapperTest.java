package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.mapper.UserLessonMapper;
import de.schmidtdennis.mysqlspring.mapper.UserMapper;
import de.schmidtdennis.mysqlspring.model.UserLesson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:META-INF/application-test.properties")
@SpringJUnitConfig(locations = "classpath:META-INF/test-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // The spring application context will be considered "dirty" before each test method, and will be rebuilt. It means that
// your autowired beans will not carry any data between test cases.
public class UserLessonMapperTest {

    @Autowired
    private UserLessonMapper userLessonMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void should_add_userLesson(){
        // GIVEN
        Integer userid = 1;
        Integer lessonId = 3;

        // WHEN
        userLessonMapper.addUserLesson(userid, lessonId);
        UserLesson userLesson = userLessonMapper.getSpecificUserLesson(userid, lessonId);

        // THEN
        assertThat(userLesson).isNotNull();
        assertThat(userLesson.getUser().getFirstName()).isEqualTo("John");
        assertThat(userLesson.getUser().getLastName()).isEqualTo("Doe");
        assertThat(userLesson.getUser().getEmail()).isEqualTo("john.doe@mail.de");
        assertThat(userLesson.getLesson().getId()).isEqualTo(3);
        assertThat(userLesson.getLesson().getTitle()).isEqualTo("The Public Security Alarm");
        assertThat(userLesson.getLesson().getDiscussion()).isEqualTo("Discussion for The Public Security Alarm");
        assertThat(userLesson.getLesson().getDifficulty().getId()).isEqualTo(1);
        assertThat(userLesson.getLesson().getDifficulty().getDescription()).isEqualTo("Absolute Beginners");
    }

    @Test
    public void should_get_userlessons_by_userId(){
        // GIVEN
        Integer userid = 1;
        Integer lessonId3 = 3;
        Integer lessonId4 = 4;
        userLessonMapper.addUserLesson(userid, lessonId3);
        userLessonMapper.addUserLesson(userid, lessonId4);

        // WHEN
        List<UserLesson> userLessons = userLessonMapper.getUserLessonsByUserId(userid);

        // THEN
        assertThat(userLessons.size()).isEqualTo(2);

        assertThat(userLessons.get(0).getUser().getFirstName()).isEqualTo("John");
        assertThat(userLessons.get(0).getUser().getLastName()).isEqualTo("Doe");
        assertThat(userLessons.get(0).getUser().getEmail()).isEqualTo("john.doe@mail.de");
        assertThat(userLessons.get(0).getLesson().getId()).isEqualTo(4);
        assertThat(userLessons.get(0).getLesson().getTitle()).isEqualTo("Shattered Dreams");
        assertThat(userLessons.get(0).getLesson().getDiscussion()).isEqualTo("Discussion for Shattered Dreams");
        assertThat(userLessons.get(0).getLesson().getDifficulty().getId()).isEqualTo(2);
        assertThat(userLessons.get(0).getLesson().getDifficulty().getDescription()).isEqualTo("Elementary");

        assertThat(userLessons.get(1).getUser().getFirstName()).isEqualTo("John");
        assertThat(userLessons.get(1).getUser().getLastName()).isEqualTo("Doe");
        assertThat(userLessons.get(1).getUser().getEmail()).isEqualTo("john.doe@mail.de");
        assertThat(userLessons.get(1).getLesson().getId()).isEqualTo(3);
        assertThat(userLessons.get(1).getLesson().getTitle()).isEqualTo("The Public Security Alarm");
        assertThat(userLessons.get(1).getLesson().getDiscussion()).isEqualTo("Discussion for The Public Security Alarm");
        assertThat(userLessons.get(1).getLesson().getDifficulty().getId()).isEqualTo(1);
        assertThat(userLessons.get(1).getLesson().getDifficulty().getDescription()).isEqualTo("Absolute Beginners");
    }

    @Test
    public void should_get_userlessons_by_userEmail(){
        // GIVEN
        Integer userid = 1;
        Integer lessonId3 = 3;
        Integer lessonId4 = 4;
        userLessonMapper.addUserLesson(userid, lessonId3);
        userLessonMapper.addUserLesson(userid, lessonId4);

        // WHEN
        List<UserLesson> userLessons = userLessonMapper.getUserLessonsByEmail("john.doe@mail.de");

        // THEN
        assertThat(userLessons.size()).isEqualTo(2);

        assertThat(userLessons.get(0).getUser().getFirstName()).isEqualTo("John");
        assertThat(userLessons.get(0).getUser().getLastName()).isEqualTo("Doe");
        assertThat(userLessons.get(0).getUser().getEmail()).isEqualTo("john.doe@mail.de");
        assertThat(userLessons.get(0).getLesson().getId()).isEqualTo(4);
        assertThat(userLessons.get(0).getLesson().getTitle()).isEqualTo("Shattered Dreams");
        assertThat(userLessons.get(0).getLesson().getDiscussion()).isEqualTo("Discussion for Shattered Dreams");
        assertThat(userLessons.get(0).getLesson().getDifficulty().getId()).isEqualTo(2);
        assertThat(userLessons.get(0).getLesson().getDifficulty().getDescription()).isEqualTo("Elementary");

        assertThat(userLessons.get(1).getUser().getFirstName()).isEqualTo("John");
        assertThat(userLessons.get(1).getUser().getLastName()).isEqualTo("Doe");
        assertThat(userLessons.get(1).getUser().getEmail()).isEqualTo("john.doe@mail.de");
        assertThat(userLessons.get(1).getLesson().getId()).isEqualTo(3);
        assertThat(userLessons.get(1).getLesson().getTitle()).isEqualTo("The Public Security Alarm");
        assertThat(userLessons.get(1).getLesson().getDiscussion()).isEqualTo("Discussion for The Public Security Alarm");
        assertThat(userLessons.get(1).getLesson().getDifficulty().getId()).isEqualTo(1);
        assertThat(userLessons.get(1).getLesson().getDifficulty().getDescription()).isEqualTo("Absolute Beginners");
    }

    @Test
    public void should_update_userLessonTimestamp(){
        // GIVEN
        Integer userid = 1;
        Integer lessonId = 3;
        userLessonMapper.addUserLesson(userid, lessonId);

        // WHEN
        UserLesson userLessonBefore = userLessonMapper.getSpecificUserLesson(userid, lessonId);
        int rows = userLessonMapper.updateUserLessonTimestamp(1, 3);
        UserLesson userLessonAfter = userLessonMapper.getSpecificUserLesson(userid, lessonId);

        // THEN
        assertThat(rows).isEqualTo(1);
        assertThat(userLessonBefore.getLastSeen()).isNotEqualTo(userLessonAfter.getLastSeen());
        assertThat(userLessonBefore.getLastSeen().isBefore(userLessonAfter.getLastSeen())).isTrue();
    }




}
