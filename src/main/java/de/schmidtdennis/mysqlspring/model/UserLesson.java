package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserLesson {
    private User user;
    private Lesson lesson;
    private boolean liked;
    private LocalDateTime lastSeen;
}
