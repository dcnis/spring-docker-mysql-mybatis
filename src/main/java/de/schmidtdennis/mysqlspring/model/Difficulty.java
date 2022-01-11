package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Difficulty {

    private Integer id;
    private String description;
    private List<Lesson> lesson;

}
