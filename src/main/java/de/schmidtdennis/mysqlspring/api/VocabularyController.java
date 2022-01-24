package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Vocabulary;
import de.schmidtdennis.mysqlspring.model.request.AddVocabulary;
import de.schmidtdennis.mysqlspring.model.response.AddVocabularyResponse;
import de.schmidtdennis.mysqlspring.service.VocabularyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VocabularyController {

    private final VocabularyService vocabularyService;

    public VocabularyController(VocabularyService vocabularyService){
        this.vocabularyService = vocabularyService;
    }

    @GetMapping("vocabulary")
    public List<Vocabulary> getVocabularyOfLesson(@RequestParam("lessonId") Integer lessonId){
        return vocabularyService.getVocabularyOfLesson(lessonId);
    }

    @PostMapping("vocabulary/add")
    public AddVocabularyResponse addVocabularytoLesson(@RequestBody @Valid AddVocabulary request){
        int insertedVocabularies = vocabularyService.addVocabularyToLesson(request);
        return new AddVocabularyResponse(request.getLessonId(), insertedVocabularies);
    }
}
