package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Lesson {

    private Integer id;
    private String title;
    private String discussion;
    private Difficulty difficulty;
    private String thumbnail;
    private String audio;
    private List<UserLesson> userLessons;

}
