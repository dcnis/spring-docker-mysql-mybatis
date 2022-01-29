package de.schmidtdennis.mysqlspring.service;

import de.schmidtdennis.mysqlspring.mapper.VocabularyMapper;
import de.schmidtdennis.mysqlspring.model.Vocabulary;
import de.schmidtdennis.mysqlspring.model.request.AddVocabulary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyService {

    private final VocabularyMapper vocabularyMapper;
    private final RedisService redisService;

    public VocabularyService(VocabularyMapper vocabularyMapper, RedisService redisService) {
        this.vocabularyMapper = vocabularyMapper;
        this.redisService = redisService;
    }

    public List<Vocabulary> getVocabularyOfLesson(Integer lessonId) {
        return vocabularyMapper.getVocabularyOfLesson(lessonId);
    }

    public int addVocabularyToLesson(AddVocabulary request) {
        int insertedRows = 0;
        for (int i = 0; i < request.getVocabulary().size(); i++) {
            insertedRows += vocabularyMapper.addVocabulary(request.getLessonId(), request.getVocabulary().get(i));
        }

        redisService.refreshAllLessons();
        redisService.updateSingleLessonIfNecessary(request.getLessonId());

        return insertedRows;
    }

}
