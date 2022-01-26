package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Difficulty;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.model.response.AddLessonResponse;
import de.schmidtdennis.mysqlspring.service.LessonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LessonControllerTest {

    @Mock
    private LessonService lessonService;

    @Mock
    private LessonMapper lessonMapper;


    @InjectMocks
    private LessonController testee;

    @Test
    public void should_getAllLessons(){
        // GIVEN

        // WHEN
        testee.getAllLessons(null);

        // THEN
        Mockito.verify(lessonService, Mockito.times(1)).getAllLessons();
    }

    @Test
    public void should_get_single_lesson(){
        // GIVEN
        Lesson mockLesson = new Lesson();
        mockLesson.setId(1);
        mockLesson.setDifficulty(new Difficulty(1, "Easy"));
        mockLesson.setTitle("The golden goose");

        Mockito.when(lessonService.getLesson(3)).thenReturn(mockLesson);

        // WHEN
        Lesson lesson = testee.getLesson(3);

        // THEN
        Mockito.verify(lessonService, Mockito.times(1)).getLesson(3);
        assertThat(lesson.getId()).isEqualTo(1);
        assertThat(lesson.getDifficulty().getId()).isEqualTo(1);
        assertThat(lesson.getDifficulty().getDescription()).isEqualTo("Easy");
        assertThat(lesson.getTitle()).isEqualTo("The golden goose");
    }

    @Test
    public void should_add_lesson(){
        // GIVEN
        Lesson mockLesson = new Lesson();
        mockLesson.setId(2);
        mockLesson.setDifficulty(new Difficulty(1, "Easy"));
        mockLesson.setTitle("The golden goose");
        mockLesson.setDiscussion("The golden goose Discussion");

        Mockito.when(lessonService.addLesson(mockLesson)).thenReturn(1);

        // WHEN
        AddLessonResponse response = testee.addLesson(mockLesson);

        // THEN
        Mockito.verify(lessonService, Mockito.times(1)).addLesson(mockLesson);
        assertThat(response.getInsertedRows()).isEqualTo(1);
        assertThat(response.getLessonId()).isEqualTo(2);
    }

}
