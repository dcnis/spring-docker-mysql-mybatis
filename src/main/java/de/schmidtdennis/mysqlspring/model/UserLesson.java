package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserLesson {
    private Integer id;
    private User userId;
    private Lesson lessonId;
    private LocalDateTime lastSeen;
    private boolean liked;
}
