package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Vocabulary;
import de.schmidtdennis.mysqlspring.model.request.AddVocabulary;
import de.schmidtdennis.mysqlspring.model.response.AddVocabularyResponse;
import de.schmidtdennis.mysqlspring.service.VocabularyService;
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
    private VocabularyService vocabularyService;

    @InjectMocks
    private VocabularyController testee;

    @Test
    public void should_get_vocabulary_of_lesson(){
        // GIVEN
        Integer lessonId = 1;
        Mockito.when(vocabularyService.getVocabularyOfLesson(lessonId)).thenReturn(new ArrayList<>());

        // WHEN
        List<Vocabulary> vocabularyOfLesson = testee.getVocabularyOfLesson(lessonId);

        // THEN
        Mockito.verify(vocabularyService, Mockito.times(1)).getVocabularyOfLesson(lessonId);
        assertThat(vocabularyOfLesson).isNotNull();
        assertThat(vocabularyOfLesson).isEmpty();
    }

    @Test
    public void should_add_single_vocabulary_to_lesson(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(5);
        List<Vocabulary> vocabulary = new ArrayList<>();
        Vocabulary singleVocabulary = new Vocabulary();
        singleVocabulary.setLessonId(5);
        singleVocabulary.setPinyin("Nǐ hǎo");
        singleVocabulary.setChinese("你好");
        singleVocabulary.setEnglish("hello");
        vocabulary.add(singleVocabulary);
        request.setVocabulary(vocabulary);

        Mockito.when(vocabularyService.addVocabularyToLesson(request)).thenReturn(1);

        // WHEN
        AddVocabularyResponse response = testee.addVocabularytoLesson(request);

        // THEN
        Mockito.verify(vocabularyService, Mockito.times(1)).addVocabularyToLesson(request);
        assertThat(response.getLessonId()).isEqualTo(5);
        assertThat(response.getInsertedVocabularies()).isEqualTo(1);
    }




}