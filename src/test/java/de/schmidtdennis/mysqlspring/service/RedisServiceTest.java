package de.schmidtdennis.mysqlspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.schmidtdennis.mysqlspring.constants.RedisKeys;
import de.schmidtdennis.mysqlspring.model.Difficulty;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.repository.RedisLessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class RedisServiceTest {

    @Mock
    private RedisLessonRepository redisLessonRepository;

    @Mock
    private RedisTemplate<String, Object> mockRedisTemplate;

    @Spy
    private ObjectMapper objectMapper;

    @Mock
    private ValueOperations<String, String> listOps;


    @InjectMocks
    private RedisService testee;

    @Test
    public void should_call_getAllSavedLessons(){
        // GIVEN

        // WHEN
        testee.getAllSavedLessons();

        // THEN
        Mockito.verify(redisLessonRepository, Mockito.times(1)).getAllSavedLessons();
    }

    @Test
    public void should_call_getAllKeys(){
        // GIVEN
        Set<String> keys = new HashSet<>();
        keys.add("one");
        keys.add("two");

        Mockito.when(mockRedisTemplate.keys("*")).thenReturn(keys);
        // WHEN
        Set<String> allKeys = testee.getAllKeys();

        // THEN
        Mockito.verify(mockRedisTemplate, Mockito.times(1)).keys("*");
        assertThat(allKeys.size()).isEqualTo(2);
        assertThat(allKeys.contains("one")).isTrue();
        assertThat(allKeys.contains("two")).isTrue();
    }

    @Test
    public void should_add_all_lessons() throws JsonProcessingException {
        // GIVEN
        List<Lesson> allLessons = new ArrayList<>();
        Lesson lessonOne = new Lesson();
        lessonOne.setId(1);
        lessonOne.setDifficulty(new Difficulty(1, "Easy"));
        lessonOne.setTitle("Lesson One");
        allLessons.add(lessonOne);

        Lesson lessonTwo = new Lesson();
        lessonTwo.setId(1);
        lessonTwo.setDifficulty(new Difficulty(2, "OK"));
        lessonTwo.setTitle("Lesson Two");
        allLessons.add(lessonTwo);

        String lessonsAsString = "[{\"id\":1,\"title\":\"Lesson One\",\"discussion\":null,\"difficulty\":{\"id\":1,\"description\":\"Easy\"},\"thumbnailUrl\":null,\"audioUrl\":null,\"vocabulary\":null},{\"id\":1,\"title\":\"Lesson Two\",\"discussion\":null,\"difficulty\":{\"id\":2,\"description\":\"OK\"},\"thumbnailUrl\":null,\"audioUrl\":null,\"vocabulary\":null}]";

        // WHEN
        testee.addAllLessons(allLessons);

        // THEN
        Mockito.verify(objectMapper, Mockito.times(1)).writeValueAsString(allLessons);
        Mockito.verify(listOps, Mockito.times(1)).set(RedisKeys.REDIS_ALL_LESSONS, lessonsAsString);

    }


}