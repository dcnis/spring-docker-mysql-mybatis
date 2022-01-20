package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.mapper.VocabularyMapper;
import de.schmidtdennis.mysqlspring.model.Vocabulary;
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
public class VocabularyMapperTest {

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @Test
    public void should_get_vocabulary_of_lesson_1(){
        // GIVEN
        // Data imported from src/test/java/resources/META-INF/test-data.sql

        // WHEN
        List<Vocabulary> vocabulary = vocabularyMapper.getVocabularyOfLesson(1);

        // THEN
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

    @Test
    public void should_get_vocabulary_of_lesson_3(){
        // GIVEN
        // Data imported from src/test/java/resources/META-INF/test-data.sql

        // WHEN
        List<Vocabulary> vocabulary = vocabularyMapper.getVocabularyOfLesson(3);

        // THEN
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



}
