package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.UserLessonMapper;
import de.schmidtdennis.mysqlspring.model.UserLesson;
import de.schmidtdennis.mysqlspring.model.response.UserLessonResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserLessonServiceTest {

    @Mock
    private UserLessonMapper userLessonMapper;

    @InjectMocks
    private UserLessonService testee;

    private static List<UserLesson> threeUserLessons;

    @BeforeAll
    static void init() {
        threeUserLessons = new ArrayList<>();
        threeUserLessons.add(new UserLesson());
        threeUserLessons.add(new UserLesson());
        threeUserLessons.add(new UserLesson());
    }

    @Test
    public void should_getUserLessonById_if_id_is_set() {
        // GIVEN
        Integer id = 5;
        Mockito.when(userLessonMapper.getUserLessonsByUserId(id)).thenReturn(threeUserLessons);

        // WHEN
        UserLessonResult userLessonResult = testee.getUserLessons(id, null);

        // THEN
        Mockito.verify(userLessonMapper, Mockito.times(1)).getUserLessonsByUserId(id);
        Mockito.verify(userLessonMapper, Mockito.times(0)).getUserLessonByEmail(ArgumentMatchers.any());
        assertThat(userLessonResult).isNotNull();
        assertThat(userLessonResult.getCount()).isEqualTo(3);
        assertThat(userLessonResult.getData()).isEqualTo(threeUserLessons);
    }

    @Test
    public void should_getUserLessonByEmail_if_id_is_null_and_email_set() {
        // GIVEN
        String email = "email";
        Mockito.when(userLessonMapper.getUserLessonByEmail(email)).thenReturn(threeUserLessons);

        // WHEN
        UserLessonResult userLessonResult = testee.getUserLessons(null, email);

        // THEN
        Mockito.verify(userLessonMapper, Mockito.times(1)).getUserLessonByEmail(email);
        Mockito.verify(userLessonMapper, Mockito.times(0)).getUserLessonsByUserId(ArgumentMatchers.any());
        assertThat(userLessonResult).isNotNull();
        assertThat(userLessonResult.getCount()).isEqualTo(3);
        assertThat(userLessonResult.getData()).isEqualTo(threeUserLessons);
    }

    @Test
    public void should_getUserLessonById_if_both_id_and_email_are_set() {
        // GIVEN
        String email = "email";
        Integer id = 5;
        Mockito.when(userLessonMapper.getUserLessonsByUserId(id)).thenReturn(threeUserLessons);

        // WHEN
        UserLessonResult userLessonResult = testee.getUserLessons(id, email);

        // THEN
        Mockito.verify(userLessonMapper, Mockito.times(1)).getUserLessonsByUserId(id);
        Mockito.verify(userLessonMapper, Mockito.times(0)).getUserLessonByEmail(email);
        assertThat(userLessonResult).isNotNull();
        assertThat(userLessonResult.getCount()).isEqualTo(3);
        assertThat(userLessonResult.getData()).isEqualTo(threeUserLessons);
    }

    @Test
    public void should_return_null_if_id_and_email_are_not_set() {
        // GIVEN

        // WHEN
        UserLessonResult userLessonResult = testee.getUserLessons(null, null);

        // THEN
        Mockito.verify(userLessonMapper, Mockito.times(0)).getUserLessonsByUserId(ArgumentMatchers.any());
        Mockito.verify(userLessonMapper, Mockito.times(0)).getUserLessonByEmail(ArgumentMatchers.any());
        assertThat(userLessonResult).isNull();
    }
}
