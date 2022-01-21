package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LessonController {

    private final LessonMapper lessonMapper;
    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonMapper lessonMapper, LessonService lessonService){
        this.lessonMapper = lessonMapper;
        this.lessonService = lessonService;
    }

    @GetMapping("lesson/getAll")
    public List<Lesson> getAllLessons(@RequestParam(value = "difficulty", required = false) Integer difficultyId){

        if(difficultyId != null){
            return lessonService.getLessonByDifficulty(difficultyId);
        }

        return lessonMapper.getAllLessons();
    }

    @GetMapping("lesson/{lessonId}")
    public Lesson getLesson(@PathVariable("lessonId") Integer lessonId){
        return lessonService.getLesson(lessonId);
    }

    @PostMapping("lesson/add")
    public String addLesson(@RequestBody Lesson lesson){

        // Add Lesson and update REDIS

        return "lesson saved";
    }

}
