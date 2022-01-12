package de.schmidtdennis.mysqlspring.api;

import de.schmidtdennis.mysqlspring.mapper.LessonMapper;
import de.schmidtdennis.mysqlspring.model.Lesson;
import de.schmidtdennis.mysqlspring.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LessonController {

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private LessonService lessonService;

    @GetMapping("lesson/getAll")
    public List<Lesson> getAllLessons(){
        return lessonMapper.getAllLessons();
    }

    @GetMapping("lesson/{lessonId}")
    public Lesson getLesson(@PathVariable("lessonId") Integer lessonId){
        return lessonService.getLesson(lessonId);
    }
}
