package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Difficulty;
import de.schmidtdennis.mysqlspring.model.Lesson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:META-INF/application-test.properties")
@SpringJUnitConfig(locations = "classpath:META-INF/test-context.xml")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // The spring application context will be considered "dirty" before each test method, and will be rebuilt. It means that
// your autowired beans will not carry any data between test cases.
public class LessonMapperTest {

    @Autowired
    private LessonMapper lessonMapper;

    @Test
    public void should_get_all_lessons(){
        // GIVEN
        // Data imported from src/test/java/resources/META-INF/test-data.sql

        // WHEN
        List<Lesson> lessons = lessonMapper.getAllLessons();

        // THEN
        assertThat(lessons.size()).isEqualTo(5);
        assertThat(lessons.get(0).getTitle()).isEqualTo("Let me do it, myself");
        assertThat(lessons.get(0).getDiscussion()).isEqualTo("Discussion for Let me do it, myself");
        assertThat(lessons.get(0).getDifficulty().getId()).isEqualTo(1);
        assertThat(lessons.get(0).getDifficulty().getDescription()).isEqualTo("Absolute Beginners");
        assertThat(lessons.get(0).getThumbnailUrl()).isEqualTo("thumbnailurl");
        assertThat(lessons.get(0).getAudioUrl()).isEqualTo("audiourl");

        assertThat(lessons.get(1).getTitle()).isEqualTo("10 signs you may have an asshole for a husband");
        assertThat(lessons.get(1).getDiscussion()).isEqualTo("Discussion for 10 signs you may have an asshole for a husband");
        assertThat(lessons.get(1).getDifficulty().getId()).isEqualTo(1);
        assertThat(lessons.get(1).getDifficulty().getDescription()).isEqualTo("Absolute Beginners");

        assertThat(lessons.get(2).getTitle()).isEqualTo("The Public Security Alarm");
        assertThat(lessons.get(2).getDiscussion()).isEqualTo("Discussion for The Public Security Alarm");
        assertThat(lessons.get(2).getDifficulty().getId()).isEqualTo(1);
        assertThat(lessons.get(2).getDifficulty().getDescription()).isEqualTo("Absolute Beginners");

        assertThat(lessons.get(3).getTitle()).isEqualTo("Shattered Dreams");
        assertThat(lessons.get(3).getDiscussion()).isEqualTo("Discussion for Shattered Dreams");
        assertThat(lessons.get(3).getDifficulty().getId()).isEqualTo(2);
        assertThat(lessons.get(3).getDifficulty().getDescription()).isEqualTo("Elementary");

        assertThat(lessons.get(4).getTitle()).isEqualTo("Travelling Light");
        assertThat(lessons.get(4).getDiscussion()).isEqualTo("Discussion for Travelling Light");
        assertThat(lessons.get(4).getDifficulty().getId()).isEqualTo(2);
        assertThat(lessons.get(4).getDifficulty().getDescription()).isEqualTo("Elementary");
    }

    @Test
    public void should_get_single_Lesson(){
        // GIVEN
        // Data imported from src/test/java/resources/META-INF/test-data.sql

        // WHEN
        Lesson lesson = lessonMapper.getLesson(1);

        // THEN
        assertThat(lesson.getTitle()).isEqualTo("Let me do it, myself");
        assertThat(lesson.getDiscussion()).isEqualTo("Discussion for Let me do it, myself");
        assertThat(lesson.getDifficulty().getId()).isEqualTo(1);
        assertThat(lesson.getDifficulty().getDescription()).isEqualTo("Absolute Beginners");
        assertThat(lesson.getThumbnailUrl()).isEqualTo("thumbnailurl");
        assertThat(lesson.getAudioUrl()).isEqualTo("audiourl");
    }

    @ParameterizedTest
    @MethodSource("provideDifficulties")
    public void should_get_difficulty_Absolute_Beginners(int id, String description){
        // GIVEN
        // Data imported from src/test/java/resources/META-INF/test-data.sql

        // WHEN
        Difficulty difficulty = lessonMapper.getDifficulty(id);

        // THEN
        assertThat(difficulty.getId()).isEqualTo(id);
        assertThat(difficulty.getDescription()).isEqualTo(description);
    }


    private static Stream<Arguments> provideDifficulties() {
        return Stream.of(
                Arguments.of(1, "Absolute Beginners"),
                Arguments.of(2, "Elementary"),
                Arguments.of(3, "Intermediate"),
                Arguments.of(4, "Advanced")
        );
    }

}
