package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.UserLesson;
import de.schmidtdennis.mysqlspring.model.response.UserLessonResult;
import de.schmidtdennis.mysqlspring.service.UserLessonService;
import org.junit.jupiter.api.Assertions;
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
class UserLessonControllerTest {

    @Mock
    private UserLessonService userLessonService;

    @InjectMocks
    private UserLessonController testee;

    private static UserLessonResult userLessonResult;
    private static List<UserLesson> threeUserLessons;

    @BeforeAll
    static void init(){
        threeUserLessons = new ArrayList<>();
        threeUserLessons.add(new UserLesson());
        threeUserLessons.add(new UserLesson());
        threeUserLessons.add(new UserLesson());

        userLessonResult = new UserLessonResult(3, threeUserLessons);
    }

    @Test
    public void should_call_UserLessonService(){
        // GIVEN
        Integer id = 5;
        String email = "email";
        Mockito.when(userLessonService.getUserLessons(id, email)).thenReturn(userLessonResult);

        // WHEN
        UserLessonResult userLessons = testee.getUserLessons(id, email);

        // THEN
        Mockito.verify(userLessonService, Mockito.times(1)).getUserLessons(id, email);
        assertThat(userLessons).isNotNull();
        assertThat(userLessons.getCount()).isEqualTo(3);
        assertThat(userLessons.getData()).isEqualTo(threeUserLessons);
    }

    @Test
    public void should_not_throw_exception_if_email_is_missing(){
        // GIVEN
        Integer id = 5;
        Mockito.when(userLessonService.getUserLessons(id, null)).thenReturn(userLessonResult);

        // WHEN
        UserLessonResult userLessons = testee.getUserLessons(id, null);

        // THEN
        Mockito.verify(userLessonService, Mockito.times(1)).getUserLessons(id, null);
        assertThat(userLessons).isNotNull();
        assertThat(userLessons.getCount()).isEqualTo(3);
        assertThat(userLessons.getData()).isEqualTo(threeUserLessons);
    }

    @Test
    public void should_not_throw_exception_if_id_is_null(){
        // GIVEN
        String email = "email";
        Mockito.when(userLessonService.getUserLessons(null, email)).thenReturn(userLessonResult);

        // WHEN
        UserLessonResult userLessons = testee.getUserLessons(null, email);

        // THEN
        Mockito.verify(userLessonService, Mockito.times(1)).getUserLessons(null, email);
        assertThat(userLessons).isNotNull();
        assertThat(userLessons.getCount()).isEqualTo(3);
        assertThat(userLessons.getData()).isEqualTo(threeUserLessons);
    }

    @Test
    public void should_throw_exception_if_id_and_email_is_null(){
        // GIVEN

        // WHEN
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> testee.getUserLessons(null, null));

        // THEN
        Mockito.verify(userLessonService, Mockito.times(0)).getUserLessons(ArgumentMatchers.any(), ArgumentMatchers.any());
        assertThat(exception.getMessage()).isEqualTo("userId und email fehlen");
    }


}