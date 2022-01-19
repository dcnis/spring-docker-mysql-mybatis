package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;

@Getter
public class Vocabulary {

    private Integer id;
    private Integer lessonId;
    private String pinyin;
    private String chinese;
    private String english;
    private Integer order;

}
