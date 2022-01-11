package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Lesson {

    private Integer id;
    private String title;
    private String discussion;
    private Difficulty difficulty;
    private String thumbnailUrl;
    private String audioUrl;

}
