package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.VocabularyMapper;
import de.schmidtdennis.mysqlspring.model.Vocabulary;
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
class VocabularyControllerTest {

    @Mock
    private VocabularyMapper vocabularyMapper;

    @InjectMocks
    private VocabularyController testee;

    @Test
    public void should_get_vocabulary_of_lesson(){
        // GIVEN
        Integer lessonId = 1;
        Mockito.when(vocabularyMapper.getVocabularyOfLesson(lessonId)).thenReturn(new ArrayList<>());

        // WHEN
        List<Vocabulary> vocabularyOfLesson = testee.getVocabularyOfLesson(lessonId);

        // THEN
        Mockito.verify(vocabularyMapper, Mockito.times(1)).getVocabularyOfLesson(lessonId);
        assertThat(vocabularyOfLesson).isNotNull();
        assertThat(vocabularyOfLesson).isEmpty();
    }

}