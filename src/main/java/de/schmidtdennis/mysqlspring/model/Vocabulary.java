package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class Vocabulary implements Serializable {

    private Integer id;
    private Integer lessonId;
    private String pinyin;
    private String chinese;
    private String english;
    private Integer vocabularyOrder;

}
