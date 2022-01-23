package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.model.response.AllLessonsResponse;
import de.schmidtdennis.mysqlspring.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String addLesson(@RequestBody @Valid Lesson lesson){

        lessonService.addLesson(lesson);

        // Add Lesson to key allLessons and specificDifficulty

        return "lesson saved";
    }

}
