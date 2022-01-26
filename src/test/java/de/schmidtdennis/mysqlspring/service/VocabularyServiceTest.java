package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.VocabularyMapper;
import de.schmidtdennis.mysqlspring.model.Vocabulary;
import de.schmidtdennis.mysqlspring.model.request.AddVocabulary;
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
class VocabularyServiceTest {

    @Mock
    private VocabularyMapper vocabularyMapper;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private VocabularyService testee;

    @Test
    public void should_call_mapper(){
        // GIVEN
        Integer lessonId = 1;
        Mockito.when(vocabularyMapper.getVocabularyOfLesson(lessonId)).thenReturn(new ArrayList<>());

        // WHEN
        List<Vocabulary> vocabularyOfLesson = testee.getVocabularyOfLesson(lessonId);

        // THEN
        Mockito.verify(vocabularyMapper, Mockito.times(1)).getVocabularyOfLesson(lessonId);
        assertThat(vocabularyOfLesson.size()).isEqualTo(0);
    }

    @Test
    public void should_add_first_vocabulary(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(2);
        List<Vocabulary> vocabularies = new ArrayList<>();
        Vocabulary konggao = new Vocabulary();
        konggao.setLessonId(2);
        konggao.setPinyin("Kǒng gāo");
        konggao.setChinese("恐高");
        konggao.setEnglish("fear of height");
        vocabularies.add(konggao);
        request.setVocabulary(vocabularies);

        Mockito.when(vocabularyMapper.addVocabulary(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(1);

        // WHEN
        int amountAddedVocabulary = testee.addVocabularyToLesson(request);

        // THEN
        Mockito.verify(vocabularyMapper, Mockito.times(1)).getVocabularyOfLesson(2);
        Mockito.verify(vocabularyMapper, Mockito.times(1)).addVocabulary(2, konggao, 1);
        Mockito.verify(redisService, Mockito.times(1)).refreshAllLessons();
        Mockito.verify(redisService, Mockito.times(1)).updateSingleLessonIfNecessary(2);
        assertThat(amountAddedVocabulary).isEqualTo(1);
    }

    @Test
    public void should_add_second_vocabulary(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(3);
        List<Vocabulary> vocabularies = new ArrayList<>();
        Vocabulary konggao = new Vocabulary(3, "Kǒng gāo", "恐高", "fear of height");
        vocabularies.add(konggao);
        request.setVocabulary(vocabularies);

        List<Vocabulary> alreadyExistingVocabulary = new ArrayList<>();
        alreadyExistingVocabulary.add(null);
        Mockito.when(vocabularyMapper.getVocabularyOfLesson(3)).thenReturn(alreadyExistingVocabulary);

        Mockito.when(vocabularyMapper.addVocabulary(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(1);

        // WHEN
        int amountAddedVocabulary = testee.addVocabularyToLesson(request);

        // THEN
        Mockito.verify(vocabularyMapper, Mockito.times(1)).getVocabularyOfLesson(3);
        Mockito.verify(vocabularyMapper, Mockito.times(1)).addVocabulary(3, konggao, 2);
        Mockito.verify(redisService, Mockito.times(1)).refreshAllLessons();
        Mockito.verify(redisService, Mockito.times(1)).updateSingleLessonIfNecessary(3);
        assertThat(amountAddedVocabulary).isEqualTo(1);
    }

    @Test
    public void should_add_two_vocabularies(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(3);
        List<Vocabulary> vocabularies = new ArrayList<>();
        Vocabulary konggao = new Vocabulary(3, "Kǒng gāo", "恐高", "fear of height");
        Vocabulary taotai = new Vocabulary(3, "Táotài", "淘汰", "to eliminate (by selection); to knock out (in a competition)");
        vocabularies.add(konggao);
        vocabularies.add(taotai);
        request.setVocabulary(vocabularies);

        List<Vocabulary> alreadyExistingVocabulary = new ArrayList<>();
        alreadyExistingVocabulary.add(null);
        Mockito.when(vocabularyMapper.getVocabularyOfLesson(3)).thenReturn(alreadyExistingVocabulary);

        Mockito.when(vocabularyMapper.addVocabulary(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(1);

        // WHEN
        int amountAddedVocabulary = testee.addVocabularyToLesson(request);

        // THEN
        Mockito.verify(vocabularyMapper, Mockito.times(1)).getVocabularyOfLesson(3);
        Mockito.verify(vocabularyMapper, Mockito.times(2)).addVocabulary(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.anyInt());
        Mockito.verify(vocabularyMapper, Mockito.times(1)).addVocabulary(3, konggao, 2);
        Mockito.verify(vocabularyMapper, Mockito.times(1)).addVocabulary(3, taotai, 3);
        Mockito.verify(redisService, Mockito.times(1)).refreshAllLessons();
        Mockito.verify(redisService, Mockito.times(1)).updateSingleLessonIfNecessary(3);
        assertThat(amountAddedVocabulary).isEqualTo(2);
    }

}