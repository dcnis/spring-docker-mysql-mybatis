package de.schmidtdennis.mysqlspring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Vocabulary implements Serializable {

    private Integer id;

    @NotNull
    private Integer lessonId;

    @NotNull
    private String pinyin;

    @NotNull
    private String chinese;

    @NotNull
    private String english;

    private Integer vocabularyOrder;

    public Vocabulary(Integer lessonId, String pinyin, String chinese, String english){
        this.lessonId = lessonId;
        this.pinyin = pinyin;
        this.chinese = chinese;
        this.english = english;
    }

}
