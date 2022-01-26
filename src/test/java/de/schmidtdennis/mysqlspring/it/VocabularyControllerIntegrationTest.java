package de.schmidtdennis.mysqlspring.it;

import de.schmidtdennis.mysqlspring.model.Vocabulary;
import de.schmidtdennis.mysqlspring.model.request.AddVocabulary;
import de.schmidtdennis.mysqlspring.model.response.AddVocabularyResponse;
import de.schmidtdennis.mysqlspring.model.response.ErrorResponse;
import de.schmidtdennis.mysqlspring.service.VocabularyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VocabularyControllerIntegrationTest {

    @MockBean
    private VocabularyService vocabularyService;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void should_throw_error_if_lessonId_missing(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(null);
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
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.postForEntity("/vocabulary/add", request, ErrorResponse.class);


        // THEN
        assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getBody()).isNotNull();
        assertThat(errorResponse.getBody().getError()).isEqualTo("Validation failed for argument [0] in public de.schmidtdennis.mysqlspring.model.response.AddVocabularyResponse de.schmidtdennis.mysqlspring.api.VocabularyController.addVocabularytoLesson(de.schmidtdennis.mysqlspring.model.request.AddVocabulary): [Field error in object 'addVocabulary' on field 'lessonId': rejected value [null]; codes [NotNull.addVocabulary.lessonId,NotNull.lessonId,NotNull.java.lang.Integer,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [addVocabulary.lessonId,lessonId]; arguments []; default message [lessonId]]; default message [must not be null]] ");
    }

    @Test
    public void should_throw_error_if_Pinyin_missing(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(5);
        List<Vocabulary> vocabulary = new ArrayList<>();
        Vocabulary singleVocabulary = new Vocabulary();
        singleVocabulary.setLessonId(5);
        singleVocabulary.setPinyin(null); // <--------- is null
        singleVocabulary.setChinese("你好");
        singleVocabulary.setEnglish("hello");
        vocabulary.add(singleVocabulary);
        request.setVocabulary(vocabulary);

        Mockito.when(vocabularyService.addVocabularyToLesson(request)).thenReturn(1);

        // WHEN
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.postForEntity("/vocabulary/add", request, ErrorResponse.class);


        // THEN
        assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getBody()).isNotNull();
        assertThat(errorResponse.getBody().getError()).isEqualTo("Validation failed for argument [0] in public de.schmidtdennis.mysqlspring.model.response.AddVocabularyResponse de.schmidtdennis.mysqlspring.api.VocabularyController.addVocabularytoLesson(de.schmidtdennis.mysqlspring.model.request.AddVocabulary): [Field error in object 'addVocabulary' on field 'vocabulary[0].pinyin': rejected value [null]; codes [NotNull.addVocabulary.vocabulary[0].pinyin,NotNull.addVocabulary.vocabulary.pinyin,NotNull.vocabulary[0].pinyin,NotNull.vocabulary.pinyin,NotNull.pinyin,NotNull.java.lang.String,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [addVocabulary.vocabulary[0].pinyin,vocabulary[0].pinyin]; arguments []; default message [vocabulary[0].pinyin]]; default message [must not be null]] ");
    }

    @Test
    public void should_throw_error_if_Chinese_missing(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(5);
        List<Vocabulary> vocabulary = new ArrayList<>();
        Vocabulary singleVocabulary = new Vocabulary();
        singleVocabulary.setLessonId(5);
        singleVocabulary.setPinyin("Nǐ hǎo");
        singleVocabulary.setChinese(null); // <--------- is null
        singleVocabulary.setEnglish("hello");
        vocabulary.add(singleVocabulary);
        request.setVocabulary(vocabulary);

        Mockito.when(vocabularyService.addVocabularyToLesson(request)).thenReturn(1);

        // WHEN
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.postForEntity("/vocabulary/add", request, ErrorResponse.class);


        // THEN
        assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getBody()).isNotNull();
        assertThat(errorResponse.getBody().getError()).isEqualTo("Validation failed for argument [0] in public de.schmidtdennis.mysqlspring.model.response.AddVocabularyResponse de.schmidtdennis.mysqlspring.api.VocabularyController.addVocabularytoLesson(de.schmidtdennis.mysqlspring.model.request.AddVocabulary): [Field error in object 'addVocabulary' on field 'vocabulary[0].chinese': rejected value [null]; codes [NotNull.addVocabulary.vocabulary[0].chinese,NotNull.addVocabulary.vocabulary.chinese,NotNull.vocabulary[0].chinese,NotNull.vocabulary.chinese,NotNull.chinese,NotNull.java.lang.String,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [addVocabulary.vocabulary[0].chinese,vocabulary[0].chinese]; arguments []; default message [vocabulary[0].chinese]]; default message [must not be null]] ");
    }


    @Test
    public void should_throw_error_if_English_missing(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(5);
        List<Vocabulary> vocabulary = new ArrayList<>();
        Vocabulary singleVocabulary = new Vocabulary();
        singleVocabulary.setLessonId(5);
        singleVocabulary.setPinyin("Nǐ hǎo");
        singleVocabulary.setChinese("你好>");
        singleVocabulary.setEnglish(null); // <--------- is null
        vocabulary.add(singleVocabulary);
        request.setVocabulary(vocabulary);

        Mockito.when(vocabularyService.addVocabularyToLesson(request)).thenReturn(1);

        // WHEN
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.postForEntity("/vocabulary/add", request, ErrorResponse.class);

        // THEN
        assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(errorResponse.getBody()).isNotNull();
        assertThat(errorResponse.getBody().getError()).isEqualTo("Validation failed for argument [0] in public de.schmidtdennis.mysqlspring.model.response.AddVocabularyResponse de.schmidtdennis.mysqlspring.api.VocabularyController.addVocabularytoLesson(de.schmidtdennis.mysqlspring.model.request.AddVocabulary): [Field error in object 'addVocabulary' on field 'vocabulary[0].english': rejected value [null]; codes [NotNull.addVocabulary.vocabulary[0].english,NotNull.addVocabulary.vocabulary.english,NotNull.vocabulary[0].english,NotNull.vocabulary.english,NotNull.english,NotNull.java.lang.String,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [addVocabulary.vocabulary[0].english,vocabulary[0].english]; arguments []; default message [vocabulary[0].english]]; default message [must not be null]] ");
    }

    @Test
    public void should_not_throw_error_when_all_fields_are_valid(){
        // GIVEN
        AddVocabulary request = new AddVocabulary();
        request.setLessonId(5);
        List<Vocabulary> vocabulary = new ArrayList<>();
        Vocabulary singleVocabulary = new Vocabulary();
        singleVocabulary.setLessonId(5);
        singleVocabulary.setPinyin("Nǐ hǎo");
        singleVocabulary.setChinese("你好>");
        singleVocabulary.setEnglish("hello");
        vocabulary.add(singleVocabulary);
        request.setVocabulary(vocabulary);

        Mockito.when(vocabularyService.addVocabularyToLesson(ArgumentMatchers.any())).thenReturn(1);

        // WHEN
        ResponseEntity<AddVocabularyResponse> response = restTemplate.postForEntity("/vocabulary/add", request, AddVocabularyResponse.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getLessonId()).isEqualTo(5);
        assertThat(response.getBody().getInsertedVocabularies()).isEqualTo(1);
    }

}
