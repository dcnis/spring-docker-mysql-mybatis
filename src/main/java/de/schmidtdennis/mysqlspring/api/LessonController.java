package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.model.response.AddLessonResponse;
import de.schmidtdennis.mysqlspring.model.response.AllLessonsResponse;
import de.schmidtdennis.mysqlspring.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @GetMapping("lesson/getAll")
    public AllLessonsResponse getAllLessons(@RequestParam(value = "difficulty", required = false) Integer difficultyId){

        if(difficultyId != null){
            return lessonService.getLessonByDifficulty(difficultyId);
        }

        return lessonService.getAllLessons();
    }

    @GetMapping("lesson/{lessonId}")
    public Lesson getLesson(@PathVariable("lessonId") Integer lessonId){
        return lessonService.getLesson(lessonId);
    }

    @PostMapping("lesson/add")
    public AddLessonResponse addLesson(@RequestBody @Valid Lesson lesson){
        Integer insertedRows = lessonService.addLesson(lesson);
        return new AddLessonResponse(insertedRows, lesson.getId());
    }

}
