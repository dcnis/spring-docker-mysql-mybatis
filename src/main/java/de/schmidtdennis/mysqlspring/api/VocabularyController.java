package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.VocabularyMapper;
import de.schmidtdennis.mysqlspring.model.Vocabulary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VocabularyController {

    @Autowired
    private VocabularyMapper vocabularyMapper;

    @GetMapping("vocabulary")
    public List<Vocabulary> getVocabularyOfLesson(@RequestParam("lessonId") Integer lessonId){
        return vocabularyMapper.getVocabularyOfLesson(lessonId);
    }
}
