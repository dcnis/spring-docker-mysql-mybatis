package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.service.RedisService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RedisControllerTest {

    @Mock
    private RedisService redisService;

    @InjectMocks
    private RedisController testee;

    @Test
    public void should_return_all_keys_from_redis(){
        // GIVEN
        Set<String> mockSet = new HashSet<>();
        mockSet.add("One");
        mockSet.add("Two");

        Mockito.when(redisService.getAllKeys()).thenReturn(mockSet);

        // WHEN
        Set<String> keys = testee.getAllKeys();

        // THEN
        Mockito.verify(redisService, Mockito.times(1)).getAllKeys();
        assertThat(keys).isEqualTo(mockSet);
    }

    @Test
    public void should_return_all_saved_lessons_from_redis(){
        // GIVEN
        TreeMap<Integer, Lesson> lessons = new TreeMap<>();
        Lesson lessonOne = new Lesson();
        lessonOne.setTitle("lessonOne");
        lessonOne.setId(1);

        Lesson lessonTwo = new Lesson();
        lessonTwo.setTitle("lessonTwo");
        lessonTwo.setId(2);

        lessons.put(1, lessonOne);
        lessons.put(2, lessonTwo);

        Mockito.when(redisService.getAllSavedLessons()).thenReturn(lessons);

        // WHEN
        TreeMap<Integer, Lesson> allSavedLessons = testee.getAllSavedLessons();

        // THEN
        Mockito.verify(redisService, Mockito.times(1)).getAllSavedLessons();
        assertThat(allSavedLessons.size()).isEqualTo(2);
        assertThat(allSavedLessons.get(1).getId()).isEqualTo(1);
        assertThat(allSavedLessons.get(1).getTitle()).isEqualTo("lessonOne");

        assertThat(allSavedLessons.get(2).getId()).isEqualTo(2);
        assertThat(allSavedLessons.get(2).getTitle()).isEqualTo("lessonTwo");

    }

}