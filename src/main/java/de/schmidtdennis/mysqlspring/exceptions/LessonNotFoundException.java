package de.schmidtdennis.mysqlspring.exceptions;

public class LessonNotFoundException extends RuntimeException {

    public LessonNotFoundException(Integer lessonId){
        super(String.format("Lesson with id=%s does not exist", lessonId));
    }

}
