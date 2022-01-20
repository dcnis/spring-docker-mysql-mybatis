package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.repository.RedisLessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

    @Mock
    private RedisLessonRepository redisLessonRepository;

    @Mock
    private LessonMapper lessonMapper;

    @InjectMocks
    private LessonService testee;

    @Test
    public void should_getLessonByDifficulty() {
        // GIVEN
        Integer difficultyId = 1;
        Mockito.when(lessonMapper.getLessonByDifficulty(difficultyId)).thenReturn(new ArrayList<>());

        // WHEN
        List<Lesson> lessons = testee.getLessonByDifficulty(difficultyId);

        // THEN
        Mockito.verify(lessonMapper, Mockito.times(0)).getDifficulty(difficultyId);
        assertThat(lessons).isNotNull();
    }

    @Test
    public void should_get_lesson_from_DB_and_save_it_to_REDIS() {
        // GIVEN
        Integer lessonId = 1;
        Lesson mockLesson = new Lesson();
        mockLesson.setId(0);
        mockLesson.setTitle("From Zero to One");
        Mockito.when(redisLessonRepository.findLesson(lessonId)).thenReturn(null);
        Mockito.when(lessonMapper.getLesson(lessonId)).thenReturn(mockLesson);

        // WHEN
        Lesson lesson = testee.getLesson(lessonId);

        // THEN
        Mockito.verify(lessonMapper, Mockito.times(1)).getLesson(lessonId);
        Mockito.verify(redisLessonRepository, Mockito.times(1)).saveLesson(mockLesson);
        assertThat(lesson.getTitle()).isEqualTo("From Zero to One");
        assertThat(lesson.getId()).isEqualTo(0);
    }

    @Test
    public void should_get_lesson_from_REDIS() {
        // GIVEN
        Integer lessonId = 1;
        Lesson mockLesson = new Lesson();
        mockLesson.setId(0);
        mockLesson.setTitle("From Zero to One");
        Mockito.when(redisLessonRepository.findLesson(lessonId)).thenReturn(mockLesson);

        // WHEN
        Lesson lesson = testee.getLesson(lessonId);

        // THEN
        Mockito.verify(redisLessonRepository, Mockito.times(1)).findLesson(lessonId);
        Mockito.verify(lessonMapper, Mockito.times(0)).getLesson(lessonId);
        Mockito.verify(redisLessonRepository, Mockito.times(0)).saveLesson(mockLesson);
        assertThat(lesson.getTitle()).isEqualTo("From Zero to One");
        assertThat(lesson.getId()).isEqualTo(0);
    }


}
