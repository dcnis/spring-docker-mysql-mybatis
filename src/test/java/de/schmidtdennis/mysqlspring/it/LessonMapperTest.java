package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Difficulty;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.model.Vocabulary;
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
        assertThat(lessons.get(0).getAudioUrl()).isEqualTo("http://localhost:3000/audio/1");

        this.checkVocabularyOfLessonOne(lessons.get(0));

        assertThat(lessons.get(1).getTitle()).isEqualTo("10 signs you may have an asshole for a husband");
        assertThat(lessons.get(1).getDiscussion()).isEqualTo("Discussion for 10 signs you may have an asshole for a husband");
        assertThat(lessons.get(1).getDifficulty().getId()).isEqualTo(1);
        assertThat(lessons.get(1).getDifficulty().getDescription()).isEqualTo("Absolute Beginners");

        assertThat(lessons.get(2).getTitle()).isEqualTo("The Public Security Alarm");
        assertThat(lessons.get(2).getDiscussion()).isEqualTo("Discussion for The Public Security Alarm");
        assertThat(lessons.get(2).getDifficulty().getId()).isEqualTo(1);
        assertThat(lessons.get(2).getDifficulty().getDescription()).isEqualTo("Absolute Beginners");
        this.checkVocabularyOfLessonThree(lessons.get(2));

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
        assertThat(lesson.getAudioUrl()).isEqualTo("http://localhost:3000/audio/1");

        assertThat(lesson.getDialog().size()).isEqualTo(4);
        assertThat(lesson.getDialog().get(0).getLessonId()).isEqualTo(1);
        assertThat(lesson.getDialog().get(0).getDialogOrder()).isEqualTo(1);
        assertThat(lesson.getDialog().get(0).getSpeaker()).isEqualTo("安吉");
        assertThat(lesson.getDialog().get(0).getPinyin()).isEqualTo("Nǐ hǎo");
        assertThat(lesson.getDialog().get(0).getChinese()).isEqualTo("你好");
        assertThat(lesson.getDialog().get(0).getEnglish()).isEqualTo("Hello!");

        assertThat(lesson.getDialog().get(1).getLessonId()).isEqualTo(1);
        assertThat(lesson.getDialog().get(1).getDialogOrder()).isEqualTo(2);
        assertThat(lesson.getDialog().get(1).getSpeaker()).isEqualTo("孙硕");
        assertThat(lesson.getDialog().get(1).getPinyin()).isEqualTo("Zhè shì shéi");
        assertThat(lesson.getDialog().get(1).getChinese()).isEqualTo("这是谁");
        assertThat(lesson.getDialog().get(1).getEnglish()).isEqualTo("Who is it?");

        assertThat(lesson.getDialog().get(2).getLessonId()).isEqualTo(1);
        assertThat(lesson.getDialog().get(2).getDialogOrder()).isEqualTo(3);
        assertThat(lesson.getDialog().get(2).getSpeaker()).isEqualTo("安吉");
        assertThat(lesson.getDialog().get(2).getPinyin()).isEqualTo("Nǐ lǎolao láile");
        assertThat(lesson.getDialog().get(2).getChinese()).isEqualTo("你姥姥来了");
        assertThat(lesson.getDialog().get(2).getEnglish()).isEqualTo("Your grandmom is it!");

        assertThat(lesson.getDialog().get(3).getLessonId()).isEqualTo(1);
        assertThat(lesson.getDialog().get(3).getDialogOrder()).isEqualTo(4);
        assertThat(lesson.getDialog().get(3).getSpeaker()).isEqualTo("孙硕");
        assertThat(lesson.getDialog().get(3).getPinyin()).isEqualTo("Tā mā de");
        assertThat(lesson.getDialog().get(3).getChinese()).isEqualTo("他妈的");
        assertThat(lesson.getDialog().get(3).getEnglish()).isEqualTo("Damn!");

        this.checkVocabularyOfLessonOne(lesson);
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

    private void checkVocabularyOfLessonOne(Lesson lesson) {
        List<Vocabulary> vocabulary = lesson.getVocabulary();
        assertThat(vocabulary.size()).isEqualTo(4);
        assertThat(vocabulary.get(0).getLessonId()).isEqualTo(1);
        assertThat(vocabulary.get(0).getPinyin()).isEqualTo("èrshíyī");
        assertThat(vocabulary.get(0).getChinese()).isEqualTo("二十一");
        assertThat(vocabulary.get(0).getEnglish()).isEqualTo("21");
        assertThat(vocabulary.get(0).getVocabularyOrder()).isEqualTo(1);

        assertThat(vocabulary.get(1).getLessonId()).isEqualTo(1);
        assertThat(vocabulary.get(1).getPinyin()).isEqualTo("kuài");
        assertThat(vocabulary.get(1).getChinese()).isEqualTo("块");
        assertThat(vocabulary.get(1).getEnglish()).isEqualTo("(n.) piece");
        assertThat(vocabulary.get(1).getVocabularyOrder()).isEqualTo(2);

        assertThat(vocabulary.get(2).getLessonId()).isEqualTo(1);
        assertThat(vocabulary.get(2).getPinyin()).isEqualTo("qián");
        assertThat(vocabulary.get(2).getChinese()).isEqualTo("钱");
        assertThat(vocabulary.get(2).getEnglish()).isEqualTo("(n.) money");
        assertThat(vocabulary.get(2).getVocabularyOrder()).isEqualTo(3);

        assertThat(vocabulary.get(3).getLessonId()).isEqualTo(1);
        assertThat(vocabulary.get(3).getPinyin()).isEqualTo("Wǒ");
        assertThat(vocabulary.get(3).getChinese()).isEqualTo("我");
        assertThat(vocabulary.get(3).getEnglish()).isEqualTo("I");
        assertThat(vocabulary.get(3).getVocabularyOrder()).isEqualTo(4);
    }

    private void checkVocabularyOfLessonThree(Lesson lesson) {
        List<Vocabulary> vocabulary = lesson.getVocabulary();
        assertThat(vocabulary.size()).isEqualTo(4);
        assertThat(vocabulary.get(0).getLessonId()).isEqualTo(3);
        assertThat(vocabulary.get(0).getPinyin()).isEqualTo("Tàitǎnníkè hào");
        assertThat(vocabulary.get(0).getChinese()).isEqualTo("泰坦尼克号");
        assertThat(vocabulary.get(0).getEnglish()).isEqualTo("(n.) Titanic");
        assertThat(vocabulary.get(0).getVocabularyOrder()).isEqualTo(1);

        assertThat(vocabulary.get(1).getLessonId()).isEqualTo(3);
        assertThat(vocabulary.get(1).getPinyin()).isEqualTo("zánmen");
        assertThat(vocabulary.get(1).getChinese()).isEqualTo("咱们");
        assertThat(vocabulary.get(1).getEnglish()).isEqualTo("we");
        assertThat(vocabulary.get(1).getVocabularyOrder()).isEqualTo(2);

        assertThat(vocabulary.get(2).getLessonId()).isEqualTo(3);
        assertThat(vocabulary.get(2).getPinyin()).isEqualTo("hǎishuǐ");
        assertThat(vocabulary.get(2).getChinese()).isEqualTo("海水");
        assertThat(vocabulary.get(2).getEnglish()).isEqualTo("(n.) seawater");
        assertThat(vocabulary.get(2).getVocabularyOrder()).isEqualTo(3);

        assertThat(vocabulary.get(3).getLessonId()).isEqualTo(3);
        assertThat(vocabulary.get(3).getPinyin()).isEqualTo("bīngchuān");
        assertThat(vocabulary.get(3).getChinese()).isEqualTo("冰川");
        assertThat(vocabulary.get(3).getEnglish()).isEqualTo("(n.) glacier (colloquially iceberg as well");
        assertThat(vocabulary.get(3).getVocabularyOrder()).isEqualTo(4);
    }

    @Test
    public void get_lesson_by_difficulty(){
        // GIVEN

        // WHEN
        List<Lesson> lessons = lessonMapper.getLessonByDifficulty(1);

        // THEN
        assertThat(lessons.size()).isEqualTo(3);
        assertThat(lessons.get(0).getTitle()).isEqualTo("Let me do it, myself");
        assertThat(lessons.get(1).getTitle()).isEqualTo("10 signs you may have an asshole for a husband");
        assertThat(lessons.get(2).getTitle()).isEqualTo("The Public Security Alarm");
    }

}
